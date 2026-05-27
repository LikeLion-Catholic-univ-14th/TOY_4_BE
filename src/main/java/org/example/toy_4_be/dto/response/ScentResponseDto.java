package org.example.toy_4_be.dto.response;

import org.example.toy_4_be.entity.Perfume;
import lombok.Getter;

@Getter
public class ScentResponseDto {
    private Long id;
    private String category;
    private String priority;
    private String term;
    private String english;
    private String description;

    public ScentResponseDto(Perfume perfume){
        this.id = perfume.getId();
        this.category = perfume.getCategory();
        this.priority = perfume.getPriority();
        this.term = perfume.getTerm();
        this.english = perfume.getEnglish();
        this.description = perfume.getDescription();
    }
}
