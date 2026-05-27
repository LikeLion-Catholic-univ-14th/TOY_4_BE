package org.example.toy_4_be.repository;

import org.example.toy_4_be.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume, Long>{
    List<Perfume> findByTermContainingOrEnglishContainingIgnoreCase(String termKeyword, String englishKeyword);
}
