package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "promotion_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
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

    @Column(name = "price", nullable = false)
    private int price;

    @Builder
    private PromotionInfo(Long id, Item item, KonbiniBrand brand, KonbiniPromotion promotion, LocalDate startDate, LocalDate endDate, int price) {
        this.id = id;
        this.item = item;
        this.brand = brand;
        this.promotion = promotion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
    public void update(KonbiniItemCreateRequest dto){
        promotion = dto.getPromotion();
        startDate = LocalDate.now();
        endDate = LocalDate.now();
        price = dto.getPricePerUnit();
    }
}