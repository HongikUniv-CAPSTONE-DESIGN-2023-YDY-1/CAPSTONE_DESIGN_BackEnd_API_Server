package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface KonbiniPromotionInfoRepository extends JpaRepository<PromotionInfo,Long> {
    List<PromotionInfo> findAllByItem_NameContains(String name);
    Optional<PromotionInfo> findByItem_NameAndBrand(String itemName, KonbiniBrand brand);
}
