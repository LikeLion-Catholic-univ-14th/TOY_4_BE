package org.example.toy_4_be.service;

import org.example.toy_4_be.dto.response.ScentResponseDto;
import org.example.toy_4_be.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScentService {
    private final PerfumeRepository perfumeRepository;

    public List<ScentResponseDto> searchGlossaries(String keyword) {
        // 빈 값이 들어오면 더 이상 아무것도 안 하고 그 자리에서 null 던지고 끝내기!
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        List<ScentResponseDto> searchResult = perfumeRepository.findByTermContainingOrEnglishContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(ScentResponseDto::new)
                .collect(Collectors.toList());

        if (searchResult.isEmpty()) {
            throw new IllegalArgumentException("해당 용어가 검색되지 않습니다.");
        }

        return searchResult;
    }
}
