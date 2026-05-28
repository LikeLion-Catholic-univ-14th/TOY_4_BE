package org.example.toy_4_be.repository;


import org.example.toy_4_be.entity.DummyPerfume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface DummyPerfumeRepository extends JpaRepository<DummyPerfume, String> {
}
