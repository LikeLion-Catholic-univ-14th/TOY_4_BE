package org.example.toy_4_be.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class RecommendationRequestDto {

    private SliderValues sliderValues;
    private List<String> preferredScents;
    private List<String> dislikedScents;
    private String usageOccasion;

    @Getter @Setter
    public static class SliderValues {
        private int weight;      // 가벼운 향(0) ↔ 무게감 있는 향(100)
        private int freshness;   // 상큼함(0) ↔ 포근함(100)
        private int sweetness;   // 깨끗함(0) ↔ 달콤함(100)
        private int warmth;      // 시원함(0) ↔ 따뜻함(100)
        private int maturity;    // 캐주얼함(0) ↔ 성숙함(100)
        private int presence;    // 은은함(0) ↔ 존재감 있는 향(100)
    }
}
