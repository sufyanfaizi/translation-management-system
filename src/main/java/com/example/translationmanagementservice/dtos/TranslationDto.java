package com.example.translationmanagementservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TranslationDto {

    @Schema(example = "en , fr")
    @JsonProperty("locale")
    private String locale;

    @Schema(example = "Adullah Turk")
    @JsonProperty("translationKey")
    private String translationKey;

    @Schema(example = "mobile , web")
    @JsonProperty("tag")
    private String tag;
    @Schema(example = "example content")
    @JsonProperty("content")
    private String content;


}
