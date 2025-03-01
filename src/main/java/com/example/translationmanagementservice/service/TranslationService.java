package com.example.translationmanagementservice.service;


import com.example.translationmanagementservice.dtos.TranslationDto;
import com.example.translationmanagementservice.dtos.UpdateTranslationDTO;
import com.example.translationmanagementservice.entity.TranslationEntity;
import com.example.translationmanagementservice.exceptions.NotFoundException;
import com.example.translationmanagementservice.repository.TranslationRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TranslationService {

    private TranslationRepository translationRepository;

    private CacheManager cacheManager;

    @CachePut(value = "translations")  // Updates cache
    public TranslationEntity createTranslation(TranslationDto translation) {

        TranslationEntity newTranslationEntity = new TranslationEntity();
        newTranslationEntity.setTranslationKey(translation.getTranslationKey());
        newTranslationEntity.setContent(translation.getContent());
        newTranslationEntity.setTags(translation.getTag());
        newTranslationEntity.setLocale(translation.getLocale());
        newTranslationEntity.setCreatedAt(Instant.now());
        translationRepository.save(newTranslationEntity);
        this.updateCache(newTranslationEntity);
        return newTranslationEntity;
    }

    private void updateCache(TranslationEntity newTranslationEntity ){
        // Manually update the cache
        Cache cache = cacheManager.getCache("translations");
        List<TranslationEntity> cachedTranslations = cache.get("translations", List.class);

// Ensure the list is mutable
        if (cachedTranslations != null) {
            cachedTranslations = new ArrayList<>(cachedTranslations); // Convert to mutable list
            cachedTranslations.add(newTranslationEntity);  // Add new entry
        } else {
            cachedTranslations = new ArrayList<>();  // Initialize if null
            cachedTranslations.add(newTranslationEntity);
        }

// Update cache with the new list
        cache.put("translations", cachedTranslations);


    }

    @CachePut(value = "translations")  // Updates cache
    public TranslationEntity updateTranslation(UpdateTranslationDTO translation) {
        return translationRepository.findById(translation.getTranslationId())
                .map(translationFound -> {
                    translationFound.setTranslationKey(translation.getTranslationKey());
                    translationFound.setContent(translation.getContent());
                    translationFound.setTags(translation.getTag());
                    translationFound.setLocale(translation.getLocale());
                    translationFound.setUpdatedAt(Instant.now());
                    return translationRepository.save(translationFound);
                })
                .orElseThrow(() -> new NotFoundException("Translation not found with ID: " + translation.getTranslationId()));
    }

    public List<TranslationEntity> getTranslationByLocale(String locale){
        return translationRepository.findByLocale(locale);
    }

    public List<TranslationEntity> searchTranslations(String key, String content, String tags, String locale) {
        return translationRepository.findByFilters(key, content, tags, locale);
    }

    public Map<String, Map<String, String>> getAllTranslations() {
        Cache cache = cacheManager.getCache("translations");
        if (cache != null) {
            List<TranslationEntity> cachedTranslations = cache.get("translations", List.class);
            if (cachedTranslations != null) {
                return cachedTranslations.stream().collect(Collectors.groupingBy(
                        TranslationEntity::getLocale,
                        Collectors.toMap(TranslationEntity::getTranslationKey, TranslationEntity::getContent)
                ));
            }
        }

        List<TranslationEntity> translations = translationRepository.findAll();
        if (cache != null) {
            cache.put("translations", translations);
        }

        return translations.stream().collect(Collectors.groupingBy(
                TranslationEntity::getLocale,
                Collectors.toMap(TranslationEntity::getTranslationKey, TranslationEntity::getContent)
        ));
    }
}
