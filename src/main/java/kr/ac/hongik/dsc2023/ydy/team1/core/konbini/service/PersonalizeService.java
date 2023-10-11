package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.event.PersonalizeEvent;

public interface PersonalizeService {
    void recommend(PersonalizeEvent personalizeEvent);
}
