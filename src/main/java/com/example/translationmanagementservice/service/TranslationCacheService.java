package com.example.translationmanagementservice.service;

import com.example.translationmanagementservice.entity.TranslationEntity;

import java.util.List;

public interface TranslationCacheService {
    void updateCache(TranslationEntity translation);
    void updateCache(List<TranslationEntity> translations);
    List<TranslationEntity> getCachedTranslations();
}