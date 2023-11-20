package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.event.PersonalizeEvent;

public interface PersonalizeService {
    void recommend(PersonalizeEvent personalizeEvent);
}
