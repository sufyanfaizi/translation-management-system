//package com.example.translationmanagementservice.utils;
//
//import com.example.translationmanagementservice.entity.TranslationEntity;
//import com.example.translationmanagementservice.repository.TranslationRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.cache.CacheManager;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class DataSeeder implements CommandLineRunner {
//    private final TranslationRepository repository;
//    private final CacheManager cacheManager;
//
//
//    public DataSeeder(TranslationRepository repository, CacheManager cacheManager) {
//        this.repository = repository;
//        this.cacheManager = cacheManager;
//    }
//
//    @Override
//    public void run(String... args) {
//        int batchSize = 1000;
//        List<TranslationEntity> translations = new ArrayList<>();
//
//        for (int i = 0; i < 110000; i++) {
//            TranslationEntity translation = new TranslationEntity();
//            translation.setTranslationKey("key " + i);
//            translation.setContent("value " + i);
//            translation.setCreatedAt(Instant.now());
//            translation.setLocale(i % 2 == 0 ? "en" : "fr"); // Randomly assign locales
//            translation.setTags("mobile");
//            translations.add(translation);
//
//            if (translations.size() >= batchSize) {
//                repository.saveAll(translations);
//                translations.clear();
//            }
//        }
//
//        if (!translations.isEmpty()) {
//            repository.saveAll(translations);
//        }
//
//        // Fetch all translations after saving
//        List<TranslationEntity> allTranslations = repository.findAll();
//
//        // Ensure cache is initialized
//        if (cacheManager.getCache("translations") != null) {
//            cacheManager.getCache("translations").put("translations", allTranslations);
//        } else {
//            System.out.println("⚠ Cache is not initialized yet!");
//        }
//
//        System.out.println("✅ Database seeded & cache updated successfully!");
//    }
//}
