package org.example.toy_4_be.controller;

import org.example.toy_4_be.dto.response.ScentResponseDto;
import org.example.toy_4_be.service.ScentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scents")

public class ScentController {
    private final ScentService scentService;

    @GetMapping("/search")
    public ResponseEntity<List<ScentResponseDto>> search(
            @RequestParam(value = "keyword", required = true) String keyword
    ) {
        List<ScentResponseDto> responses = scentService.searchGlossaries(keyword);
        return ResponseEntity.ok(responses);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
}
