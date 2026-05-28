package org.example.toy_4_be.dto.response;

import lombok.*;
import org.example.toy_4_be.entity.DummyPerfume;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponseDto {

    private String status = "SUCCESS";
    private String message = "AI 향수 추천에 성공했습니다.";
    private PerfumeData data;

    public RecommendationResponseDto(DummyPerfume perfume) {
        this.data = new PerfumeData(perfume);
    }

    @Getter @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PerfumeData {
        private String perfumeId;
        private String brandName;
        private String perfumeName;
        private List<String> scentTags; // JSON에서 ["키워드1", "키워드2"] 배열로 나감
        private String recommendationReason;
        private String simpleDescription;
        private Notes notes;    // top, middle, base
        private String purchaseLink;

        public PerfumeData(DummyPerfume perfume) {
            this.perfumeId = perfume.getPerfumeId();
            this.brandName = perfume.getBrandName();
            this.perfumeName = perfume.getPerfumeName();
            this.recommendationReason = perfume.getRecommendationReason();
            this.simpleDescription = perfume.getEasyDescription(); // 요구사항에 맞춰 '쉬운 설명' 매핑
            this.purchaseLink = perfume.getPurchaseLink();

            // "클린/머스크" -> ["클린", "머스크"] 리스트로 쪼개기
            if (perfume.getScentTags() != null) {
                this.scentTags = Arrays.asList(perfume.getScentTags().split("/"));
            }

            // 노트 정보 묶어주기
            this.notes = new Notes(perfume.getTopNote(), perfume.getMiddleNote(), perfume.getBaseNote());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notes {
        private String top;
        private String middle;
        private String base;

    }
}
