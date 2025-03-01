package com.example.translationmanagementservice.controller;


import com.example.translationmanagementservice.dtos.TranslationDto;
import com.example.translationmanagementservice.dtos.UpdateTranslationDTO;
import com.example.translationmanagementservice.entity.TranslationEntity;
import com.example.translationmanagementservice.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/translation")
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping
    public ResponseEntity<TranslationEntity> createTranslation(@RequestBody TranslationDto translation){

        return ResponseEntity.ok(translationService.createTranslation(translation));
    }
    @PutMapping
    public ResponseEntity<TranslationEntity> updateTranslation(@RequestBody UpdateTranslationDTO translation){

        return ResponseEntity.ok(translationService.updateTranslation(translation));
    }

    @GetMapping("/{locale}")
    public ResponseEntity<List<TranslationEntity>> getTranslationsByLocale(@PathVariable String locale){
        return ResponseEntity.ok(translationService.getTranslationByLocale(locale));

    }

    @GetMapping("/search")
    public ResponseEntity<List<TranslationEntity>> searchTranslations(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String locale) {
        return ResponseEntity.ok(translationService.searchTranslations(key, content, tags, locale));
    }

    @GetMapping("/export")
    public Map<String, Map<String, String>> exportTranslations() {
        return translationService.getAllTranslations();
    }
}


