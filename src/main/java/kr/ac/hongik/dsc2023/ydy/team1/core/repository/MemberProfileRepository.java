package kr.ac.hongik.dsc2023.ydy.team1.core.repository;

import java.util.Optional;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    Optional<MemberProfile> findByMember_Id(int memberID);
}
