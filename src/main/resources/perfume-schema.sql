DROP TABLE IF EXISTS dummy_perfume;

CREATE TABLE dummy_perfume (
                               perfume_id              VARCHAR(10)  PRIMARY KEY,
                               brand_name              VARCHAR(100) NOT NULL,
                               perfume_name            VARCHAR(200) NOT NULL,
                               scent_tags              VARCHAR(200),
                               simple_description      VARCHAR(500),
                               easy_description        VARCHAR(500),
                               recommendation_reason   VARCHAR(500),
                               top_note                VARCHAR(200),
                               middle_note             VARCHAR(200),
                               base_note               VARCHAR(200),
                               weight                  INT          DEFAULT 0,
                               sweetness               INT          DEFAULT 0,
                               freshness               INT          DEFAULT 0,
                               warmth                  INT          DEFAULT 0,
                               maturity                INT          DEFAULT 0,
                               mood                    VARCHAR(50),
                               disliked_conditions     VARCHAR(300),
                               recommended_situations  VARCHAR(200),
                               purchase_link           VARCHAR(500)
);