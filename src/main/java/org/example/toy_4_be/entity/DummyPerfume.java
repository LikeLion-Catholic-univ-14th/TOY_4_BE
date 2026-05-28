package org.example.toy_4_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "dummy_perfume")
@Getter
@Setter
public class DummyPerfume {

    @Id
    @Column(name = "perfume_id")
    private String perfumeId;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "perfume_name", nullable = false)
    private String perfumeName;

    @Column(name = "scent_tags")
    private String scentTags;

    @Column(name = "simple_description")
    private String simpleDescription;

    @Column(name = "easy_description")
    private String easyDescription; // schema.sql에 있던 필드 추가

    @Column(name = "recommendation_reason")
    private String recommendationReason; // schema.sql에 있던 필드 추가

    @Column(name = "top_note")
    private String topNote;

    @Column(name = "middle_note")
    private String middleNote;

    @Column(name = "base_note")
    private String baseNote;

    private int weight;
    private int sweetness;
    private int freshness;
    private int warmth;
    private int maturity;

    private String mood;

    @Column(name = "disliked_conditions")
    private String dislikedConditions;

    @Column(name = "recommended_situations")
    private String recommendedSituations;

    @Column(name = "purchase_link")
    private String purchaseLink;


}
