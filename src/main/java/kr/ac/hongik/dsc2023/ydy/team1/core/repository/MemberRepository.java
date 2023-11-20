package kr.ac.hongik.dsc2023.ydy.team1.core.repository;

import java.util.Optional;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);
}
