package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.ItemCreateRequest;

import java.util.List;


public interface PromotionService {
    void create(ItemCreateRequest itemCreateRequest);

    void createAll(List<ItemCreateRequest> requests);
}
