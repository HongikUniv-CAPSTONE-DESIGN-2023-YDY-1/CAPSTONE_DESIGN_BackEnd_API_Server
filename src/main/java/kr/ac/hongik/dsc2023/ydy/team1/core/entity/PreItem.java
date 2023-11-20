package kr.ac.hongik.dsc2023.ydy.team1.core.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.KonbiniPromotion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pre_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imgURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand_id", nullable = false)
    private KonbiniBrand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "promotion_id", nullable = false)
    private KonbiniPromotion promotion;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "price", nullable = false)
    private int pricePerUnit;
}
