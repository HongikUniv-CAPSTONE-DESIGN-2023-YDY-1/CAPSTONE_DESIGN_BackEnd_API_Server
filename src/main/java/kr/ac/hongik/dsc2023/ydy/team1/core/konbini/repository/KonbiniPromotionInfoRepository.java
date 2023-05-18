package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KonbiniPromotionInfoRepository extends JpaRepository<PromotionInfo,Long> {
}
