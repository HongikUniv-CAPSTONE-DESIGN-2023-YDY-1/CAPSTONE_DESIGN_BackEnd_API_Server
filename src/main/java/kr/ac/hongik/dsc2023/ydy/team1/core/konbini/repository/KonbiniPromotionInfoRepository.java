package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.ItemCategory;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface KonbiniPromotionInfoRepository extends JpaRepository<PromotionInfo,Long> {
    List<PromotionInfo> findAllByItem_NameContainsAndStartDateGreaterThanEqualAndEndDateGreaterThanEqual(String name, LocalDate startDate, LocalDate endDate);
    Optional<PromotionInfo> findByItem_NameAndBrand(String itemName, KonbiniBrand brand);
    @Query(
            value = "(select * from promotion_info p join item i on p.item_id = i.id where category in (\n" +
                    "select tmp.key_name from(SELECT key_name\n" +
                    "FROM (\n" +
                    "  SELECT 'BEVERAGE' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.BEVERAGE')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'SNACK' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.SNACK')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'FOOD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.FOOD')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'ICE_CREAM' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.ICE_CREAM')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'HOUSEHOLD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.HOUSEHOLD')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    ") AS key_data\n" +
                    "GROUP BY key_name\n" +
                    "ORDER BY MAX(CONVERT(key_value, SIGNED)) DESC\n" +
                    "LIMIT 1\n" +
                    ") tmp)\n" +
                    "limit 6)\n" +
                    "union all \n" +
                    "(select * from promotion_info p join item i on p.item_id = i.id where category in (\n" +
                    "select tmp.key_name from(SELECT key_name\n" +
                    "FROM (\n" +
                    "  SELECT 'BEVERAGE' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.BEVERAGE')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'SNACK' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.SNACK')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'FOOD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.FOOD')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'ICE_CREAM' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.ICE_CREAM')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    "  UNION ALL\n" +
                    "  SELECT 'HOUSEHOLD' AS key_name, JSON_UNQUOTE(JSON_EXTRACT(recommend_data, '$.HOUSEHOLD')) AS key_value FROM member_profile where member_id=:memberId\n" +
                    ") AS key_data\n" +
                    "GROUP BY key_name\n" +
                    "ORDER BY MAX(CONVERT(key_value, SIGNED)) DESC\n" +
                    "LIMIT 1 offset 1\n" +
                    ") tmp)\n" +
                    "limit 4)\n",
            nativeQuery = true
    )
    List<PromotionInfo> findByCategoryBasedPersonalizeData(@Param("memberId")int memberID);
    @Query(nativeQuery = true, value = "(select * from promotion_info p join item i on i.id = p.item_id " +
            "where start_date = :startDate and end_date = :endDate " +
            "and JSON_EXISTS(sub_category, :sub1) = true order by rand() limit 6) " +
            "union all " +
            "(select * from promotion_info p join item i on i.id = p.item_id " +
            "where start_date = :startDate and end_date = :endDate " +
            "and JSON_EXISTS(sub_category, :sub2) = true order by rand() limit 4) " +
            "order by rand()")
    List<PromotionInfo> findBySubCategoryBasedPersonalizeData(@Param("sub1") String sub1,
                                                              @Param("sub2") String sub2,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate);
    //Todo 쿼리 변경
    @Query(nativeQuery = true, value = "select * from promotion_info p join item i on i.id = p.item_id " +
            "where start_date = :today and end_date = :today " +
            "and category = :itemCategory " +
            "and JSON_EXISTS(sub_category, :subCategory) limit 10")
    List<PromotionInfo> findByRecentAccessBasedPersonalizeData(@Param("today") LocalDate today,
                                                               @Param("itemCategory")String itemCategory,
                                                               @Param("subCategory") String subCategory);

    @EntityGraph(attributePaths = {"item"})
    List<PromotionInfo> findByItem_CategoryAndStartDateAndEndDate(ItemCategory itemCategory, LocalDate startDate, LocalDate endDate);
}
