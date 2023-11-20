package kr.ac.hongik.dsc2023.ydy.team1.core.repository;

import java.util.Optional;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KonbiniItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
