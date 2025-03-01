package com.example.translationmanagementservice.repository;


import com.example.translationmanagementservice.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationEntity , Long> {

    List<TranslationEntity> findByLocale(String locale);


    @Query("SELECT t FROM TranslationEntity t WHERE " +
            "(:key IS NULL OR t.translationKey LIKE %:key%) AND " +
            "(:content IS NULL OR t.content LIKE %:content%) AND " +
            "(:tags IS NULL OR t.tags LIKE %:tags%) AND " +
            "(:locale IS NULL OR t.locale = :locale)")
    List<TranslationEntity> findByFilters(@Param("key") String key,
                                          @Param("content") String content,
                                          @Param("tags") String tags,
                                          @Param("locale") String locale);
}
