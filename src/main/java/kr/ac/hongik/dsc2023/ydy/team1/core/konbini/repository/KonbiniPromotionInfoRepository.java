package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KonbiniPromotionInfoRepository extends JpaRepository<PromotionInfo,Long> {
    List<PromotionInfo> findAllByItem_NameContainsAndStartDateGreaterThanEqualAndEndDateGreaterThanEqual(String name, LocalDate startDate, LocalDate endDate);
    Optional<PromotionInfo> findByItem_NameAndBrand(String itemName, KonbiniBrand brand);
    @Query(
            value = "(select * from promotion_info p join item i on p.item_id = i.id where category in (\n" +
                    "select tmp.key_name from(SELECT key_name\n" +
                    "FROM (\n" +
                    "  SELECT 'BEVERAGE' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.BEVERAGE')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'SNACK' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.SNACK')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'FOOD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.FOOD')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'ICE_CREAM' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.ICE_CREAM')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'HOUSEHOLD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.HOUSEHOLD')) AS key_value FROM member_profile where member_id=1\n" +
                    ") AS key_data\n" +
                    "GROUP BY key_name\n" +
                    "ORDER BY MAX(CONVERT(key_value, SIGNED)) DESC\n" +
                    "LIMIT 1\n" +
                    ") tmp)\n" +
                    "limit 3)\n" +
                    "union all \n" +
                    "(select * from promotion_info p join item i on p.item_id = i.id where category in (\n" +
                    "select tmp.key_name from(SELECT key_name\n" +
                    "FROM (\n" +
                    "  SELECT 'BEVERAGE' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.BEVERAGE')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'SNACK' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.SNACK')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'FOOD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.FOOD')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'ICE_CREAM' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.ICE_CREAM')) AS key_value FROM member_profile where member_id=1\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'HOUSEHOLD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.HOUSEHOLD')) AS key_value FROM member_profile where member_id=1\n" +
                    ") AS key_data\n" +
                    "GROUP BY key_name\n" +
                    "ORDER BY MAX(CONVERT(key_value, SIGNED)) DESC\n" +
                    "LIMIT 1 offset 1\n" +
                    ") tmp)\n" +
                    "limit 2)\n",
            nativeQuery = true
    )
    List<PromotionInfo> findByPersonalizeData(@Param("memberId")int memberID);
}
