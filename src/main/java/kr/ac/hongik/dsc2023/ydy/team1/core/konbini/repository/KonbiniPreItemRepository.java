package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PreItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KonbiniPreItemRepository extends JpaRepository<PreItem,Long> {
    Page<PreItem> findByNameContains(String keyword, Pageable pageable);
    Page<PreItem> findAll(Pageable pageable);

    void deleteAllByIdIn(List<Long> ids);
}
