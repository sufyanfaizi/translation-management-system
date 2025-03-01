package com.example.translationmanagementservice.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateTranslationDTO {

    @NotNull(message = "ID is required for update")
    private Long translationId;

    private String translationKey;
    private String content;
    private String locale;
    private String tag;

}
