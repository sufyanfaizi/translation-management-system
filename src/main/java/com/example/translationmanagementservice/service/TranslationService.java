package com.example.translationmanagementservice.service;

import com.example.translationmanagementservice.dtos.TranslationDto;
import com.example.translationmanagementservice.dtos.UpdateTranslationDTO;
import com.example.translationmanagementservice.entity.TranslationEntity;

import java.util.List;
import java.util.Map;

public interface TranslationService {
    TranslationEntity createTranslation(TranslationDto translation);
    TranslationEntity updateTranslation(UpdateTranslationDTO translation);
    List<TranslationEntity> getTranslationByLocale(String locale);
    List<TranslationEntity> searchTranslations(String key, String content, String tags, String locale);
    Map<String, Map<String, String>> getAllTranslations();
}