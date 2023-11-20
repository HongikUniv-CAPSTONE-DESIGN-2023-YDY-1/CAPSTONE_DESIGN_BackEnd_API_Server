package kr.ac.hongik.dsc2023.ydy.team1.core.event;

import static kr.ac.hongik.dsc2023.ydy.team1.core.model.PersonalizeAlg.CATEGORY_BASE;
import static kr.ac.hongik.dsc2023.ydy.team1.core.model.PersonalizeAlg.RECENT_ACCESS_BASE;
import static kr.ac.hongik.dsc2023.ydy.team1.core.model.PersonalizeAlg.SUB_CATEGORY_BASE;

import kr.ac.hongik.dsc2023.ydy.team1.core.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.PersonalizeAlg;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.service.CategoryBasedPersonalizeService;
import kr.ac.hongik.dsc2023.ydy.team1.core.service.RecentAccessBasedPersonalizeService;
import kr.ac.hongik.dsc2023.ydy.team1.core.service.SubCategoryBasedPersonalizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalizeEventHandler {
    private final CategoryBasedPersonalizeService categoryBasedPersonalizeService;
    private final SubCategoryBasedPersonalizeService subCategoryBasedPersonalizeService;
    private final RecentAccessBasedPersonalizeService recentAccessBasedPersonalizeService;
    private final MemberProfileRepository memberProfileRepository;

    @EventListener
    public void recommend(PersonalizeEvent personalizeEvent) {
        int memberID = personalizeEvent.getMemberID();
        MemberProfile memberProfile = memberProfileRepository.findByMember_Id(memberID)
                .orElseThrow(() -> new IllegalStateException("회원의 퍼스널 데이터가 없습니다."));
        PersonalizeAlg personalizeAlg = memberProfile.getPersonalizeAlg();
        if (personalizeAlg == CATEGORY_BASE) {
            categoryBasedPersonalizeService.recommend(personalizeEvent);
        }
        if (personalizeAlg == SUB_CATEGORY_BASE) {
            subCategoryBasedPersonalizeService.recommend(personalizeEvent);
        }
        if (personalizeAlg == RECENT_ACCESS_BASE) {
            recentAccessBasedPersonalizeService.recommend(personalizeEvent);
        }
    }
}
