package org.example.toy_4_be.controller;


import lombok.RequiredArgsConstructor;
import org.example.toy_4_be.dto.response.RecommendationResponseDto;
import org.example.toy_4_be.entity.DummyPerfume;
import org.example.toy_4_be.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perfumes")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/recommend")
    public ResponseEntity<RecommendationResponseDto> getPerfumeRecommendation(@RequestBody String userInput){
        // 1. 전체 흐름을 담당하는 RecommendationService를 호출해 AI가 추천한 진짜 향수 데이터(Entity)를 받아옵니다.
        DummyPerfume perfume = recommendationService.getAiRecommendation(userInput);

        // 2. 받아온 Entity를 우리가 아까 만든 Response JSON 모양(DTO)으로 예쁘게 포장합니다.
        //    이때 내부에서 "클린/머스크" -> ["클린", "머스크"]로 쪼개는 작업이 자동으로 일어납니다!
        RecommendationResponseDto response = new RecommendationResponseDto(perfume);

        // 3. 프론트엔드가 요구한 JSON 구조로 변환하여 200 OK 응답을 보냅니다.
        return ResponseEntity.ok(response);
    }
}
