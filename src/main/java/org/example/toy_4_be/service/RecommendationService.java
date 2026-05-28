package org.example.toy_4_be.service;


import lombok.RequiredArgsConstructor;
import org.example.toy_4_be.entity.DummyPerfume;
import org.example.toy_4_be.repository.DummyPerfumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final GeminiService geminiService;
    private final DummyPerfumeRepository dummyPerfumeRepository;

    public DummyPerfume getAiRecommendation(String userInput) {
        //1. DB(h2)에서 향수 25개 전체 목록을 가져옴
        List<DummyPerfume> allPerfumes = dummyPerfumeRepository.findAll();

        //2. Gemini 호출, 사용자 입력값 + 전체 향수 리스트
        String recommendedId = geminiService.recommendPerfumeId(userInput, allPerfumes);

        //AI 반환값에서 공백 삭제
        final String finalCleanId = recommendedId.trim();

        //3. AI가 골라준 ID로 내 레포에서 향수 데이터를 조회해서 리턴
        return dummyPerfumeRepository.findById(recommendedId)
                .orElseThrow(() -> new IllegalArgumentException("AI가 추천한 향수 ID를 DB에서 찾을 수 없습니다: " + finalCleanId));
    }

}
