package com.example.translationmanagementservice.service.Implementation;

import com.example.translationmanagementservice.entity.TranslationEntity;
import com.example.translationmanagementservice.service.TranslationCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslationCacheServiceImpl implements TranslationCacheService {

    private final CacheManager cacheManager;

    @Override
    public void updateCache(TranslationEntity translation) {
        Cache cache = cacheManager.getCache("translations");
        if (cache == null) return;

        List<TranslationEntity> cachedTranslations = getCachedTranslations();
        cachedTranslations.add(translation);
        cache.put("translations", cachedTranslations);
    }

    @Override
    public void updateCache(List<TranslationEntity> translations) {
        Cache cache = cacheManager.getCache("translations");
        if (cache != null) {
            cache.put("translations", translations);
        }
    }

    @Override
    public List<TranslationEntity> getCachedTranslations() {
        Cache cache = cacheManager.getCache("translations");
        if (cache == null) return new ArrayList<>();

        return Optional.ofNullable(cache.get("translations", List.class))
                .orElseGet(ArrayList::new);
    }
}
