package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberProfileRepository extends JpaRepository<MemberProfile,Long> {
    Optional<MemberProfile> findByMember_Id(int memberID);
}
