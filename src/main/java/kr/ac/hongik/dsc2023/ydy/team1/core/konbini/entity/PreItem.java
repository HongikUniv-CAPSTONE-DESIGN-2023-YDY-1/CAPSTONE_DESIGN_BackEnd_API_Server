package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pre_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARBINARY(255)")
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
