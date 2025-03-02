package com.example.translationmanagementservice.service.Implementation;

import com.example.translationmanagementservice.dtos.TranslationDto;
import com.example.translationmanagementservice.dtos.UpdateTranslationDTO;
import com.example.translationmanagementservice.entity.TranslationEntity;
import com.example.translationmanagementservice.exceptions.NotFoundException;
import com.example.translationmanagementservice.repository.TranslationRepository;
import com.example.translationmanagementservice.service.TranslationService;
import com.example.translationmanagementservice.service.TranslationCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslationServiceImp implements TranslationService {

    private final TranslationRepository translationRepository;
    private final TranslationCacheService translationCacheService;

    @Override
    public TranslationEntity createTranslation(TranslationDto translation) {
        TranslationEntity newTranslationEntity = new TranslationEntity();
        newTranslationEntity.setTranslationKey(translation.getTranslationKey());
        newTranslationEntity.setContent(translation.getContent());
        newTranslationEntity.setTags(translation.getTag());
        newTranslationEntity.setLocale(translation.getLocale());
        newTranslationEntity.setCreatedAt(Instant.now());

        translationRepository.save(newTranslationEntity);
        translationCacheService.updateCache(newTranslationEntity); // Handle caching separately

        return newTranslationEntity;
    }

    @Override
    public TranslationEntity updateTranslation(UpdateTranslationDTO translation) {
        return translationRepository.findById(translation.getTranslationId())
                .map(translationFound -> {
                    translationFound.setTranslationKey(translation.getTranslationKey());
                    translationFound.setContent(translation.getContent());
                    translationFound.setTags(translation.getTag());
                    translationFound.setLocale(translation.getLocale());
                    translationFound.setUpdatedAt(Instant.now());

                    translationRepository.save(translationFound);
                    translationCacheService.updateCache(translationFound);

                    return translationFound;
                })
                .orElseThrow(() -> new NotFoundException("Translation not found with ID: " + translation.getTranslationId()));
    }

    @Override
    public List<TranslationEntity> getTranslationByLocale(String locale) {
        return translationRepository.findByLocale(locale);
    }

    @Override
    public List<TranslationEntity> searchTranslations(String key, String content, String tags, String locale) {
        return translationRepository.findByFilters(key, content, tags, locale);
    }

    @Override
    public Map<String, Map<String, String>> getAllTranslations() {
        List<TranslationEntity> translations = translationCacheService.getCachedTranslations();
        if (translations.isEmpty()) {
            translations = translationRepository.findAll();
            translationCacheService.updateCache(translations);
        }
        return translations.stream().collect(Collectors.groupingBy(
                TranslationEntity::getLocale,
                Collectors.toMap(TranslationEntity::getTranslationKey, TranslationEntity::getContent)
        ));
    }
}