package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.event;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.PersonalizeAlg;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.AbstractPersonalizeService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.CategoryBasedPersonalizeService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.SubCategoryBasedPersonalizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.PersonalizeAlg.*;

@Component
@RequiredArgsConstructor
public class PersonalizeEventHandler{
    private final CategoryBasedPersonalizeService categoryBasedPersonalizeService;
    private final SubCategoryBasedPersonalizeService subCategoryBasedPersonalizeService;
    private final MemberProfileRepository memberProfileRepository;
    @EventListener
    public void recommend(PersonalizeEvent personalizeEvent) {
        int memberID = personalizeEvent.getMemberID();
        MemberProfile memberProfile = memberProfileRepository.findByMember_Id(memberID).orElseThrow(() -> new IllegalStateException("회원의 퍼스널 데이터가 없습니다."));
        PersonalizeAlg personalizeAlg = memberProfile.getPersonalizeAlg();
        if(personalizeAlg == CATEGORY_BASE){
            categoryBasedPersonalizeService.recommend(personalizeEvent);
        }
        if (personalizeAlg == SUB_CATEGORY_BASE){
            subCategoryBasedPersonalizeService.recommend(personalizeEvent);
        }
    }
}
