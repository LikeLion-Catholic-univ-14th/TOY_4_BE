package org.example.toy_4_be.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;  // 👈 필수! (JSON 파싱용 빈 생성자)
import lombok.AllArgsConstructor; // 👈 필수! (전체 필드 생성자)
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor  // 👈 추가해 주세요!
@AllArgsConstructor // 👈 추가해 주세요!
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String priority;
    private String term;
    private String english;
    private String description;
}