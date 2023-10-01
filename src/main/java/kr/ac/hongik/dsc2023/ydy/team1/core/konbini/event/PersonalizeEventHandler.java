package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.event;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.CategoryBasedPersonalizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalizeEventHandler{
    private final CategoryBasedPersonalizeService personalizeService;
    @EventListener
    public void recommend(PersonalizeEvent personalizeEvent) {
        personalizeService.recommend(personalizeEvent);
    }
}
