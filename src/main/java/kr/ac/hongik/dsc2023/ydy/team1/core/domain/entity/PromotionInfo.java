package kr.ac.hongik.dsc2023.ydy.team1.core.domain.entity;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini.KonbiniPromotion;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "promotion_info")
@Getter
public class PromotionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

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
}