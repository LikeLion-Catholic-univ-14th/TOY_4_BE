package org.example.toy_4_be.external;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.toy_4_be.config.GeminiConfig;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GeminiClient {
    private final RestTemplate geminiRestTemplate;
    private final GeminiConfig geminiConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendPrompt(String prompt) {
        String url = geminiConfig.getApiUrl()
                + "/" + geminiConfig.getApiModel()
                + ":generateContent?key=" + geminiConfig.getApiKey();

        // Gemini 요청 Body 구조 생성
        String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        { "text": "%s" }
                      ]
                    }
                  ],
                  "generationConfig": {
                    "temperature": 0.3,
                    "maxOutputTokens": 512
                  }
                }
                """.formatted(prompt.replace("\"", "\\\""));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = geminiRestTemplate.exchange(
                    url, HttpMethod.POST, entity, String.class
            );

            // 응답 JSON에서 텍스트 노드 추출
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode candidates = root.path("candidates");

            if (!candidates.isArray() || candidates.isEmpty()) {
                throw new RuntimeException("Gemini 응답이 비어있습니다.");
            }

            return candidates.get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            throw new RuntimeException("Gemini API 통신 실패: " + e.getMessage(), e);
        }
    }

}
