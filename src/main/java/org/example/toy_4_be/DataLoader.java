package org.example.toy_4_be;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.toy_4_be.entity.Perfume;
import org.example.toy_4_be.repository.PerfumeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PerfumeRepository perfumeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (perfumeRepository.count() > 0) return;

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("ScentData.json").getInputStream();
        List<Perfume> perfumes = objectMapper.readValue(inputStream, new TypeReference<List<Perfume>>() {});
        perfumeRepository.saveAll(perfumes);

        System.out.println("✅ " + perfumes.size() + "건 저장 완료");
    }
}