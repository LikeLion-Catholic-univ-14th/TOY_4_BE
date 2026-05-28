package org.example.toy_4_be.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_4_be.entity.DummyPerfume;
import org.example.toy_4_be.external.GeminiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GeminiService {
    private final GeminiClient geminiClient;

    private final RestTemplate restTemplate = new RestTemplate();

    public String recommendPerfumeId(String userPreference, List<DummyPerfume> perfumes) {
        //1. 컨트롤러에서 받아온 향수 리스트 텍스트로 변환 (향수 레벨 명시)
        String perfumeList = perfumes.stream()
                .map(p -> String.format(
                        "ID: %s | %s %s\n" +
                                "- 설명: %s | 태그: %s\n" +
                                "- 향 레벨(1~3 범위): 무게감(%d), 달콤함(%d), 상큼함(%d), 따뜻함(%d), 성숙함(%d)\n" +
                                "- 추천 상황: %s | 피해야 할 조건: %s",
                        p.getPerfumeId(), p.getBrandName(), p.getPerfumeName(),
                        p.getSimpleDescription(), p.getScentTags(),
                        p.getWeight(), p.getSweetness(), p.getFreshness(), p.getWarmth(), p.getMaturity(),
                        p.getRecommendedSituations(), p.getDislikedConditions()
                ))
                .collect(Collectors.joining("\n\n"));


        // 2. 프롬프트 구성 (ID만 반환!!)
        String prompt = """
                너는 사용자의 취향 데이터(슬라이더 점수 0~100)를 분석하여 최적의 향수를 골라주는 전문가야.
                아래 [사용자 취향 데이터]와 가장 잘 어울리는 향수 딱 1개의 ID를 [향수 목록]에서 선정해줘.
                
                ■ 향수 목록의 레벨(1~3) 기준 안내:
                - 1: 낮음/가벼움 (사용자 점수 기준 대략 0 ~ 35점에 해당)
                - 2: 중간/보통 (사용자 점수 기준 대략 36 ~ 65점에 해당)
                - 3: 높음/강함 (사용자 점수 기준 대략 66 ~ 100점에 해당)
                
                ■ 아주 중요한 지시사항:
                인사말이나 다른 설명은 절대 하지 말고 오직 선정된 향수의 ID(예: P001)만 딱 텍스트로 응답해줘.
                
                [향수 목록]
                %s
                
                [사용자 취향]
                %s
                """.formatted(perfumeList, userPreference);

        //3. GeminiClient에게 위임
        String aiResponse = geminiClient.sendPrompt(prompt);

        //4. 받아온 응답(ID)의 앞뒤 공백을 깎아서 리턴
        return aiResponse.trim();

    }

}
