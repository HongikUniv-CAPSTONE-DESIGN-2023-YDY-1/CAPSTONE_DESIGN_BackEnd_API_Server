package kr.ac.hongik.dsc2023.ydy.team1.core.repository;

import java.util.List;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PreItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KonbiniPreItemRepository extends JpaRepository<PreItem, Long> {
    Page<PreItem> findByNameContains(String keyword, Pageable pageable);

    Page<PreItem> findAll(Pageable pageable);

    void deleteAllByIdIn(List<Long> ids);
}
