package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import java.util.List;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.ItemCreateRequest;


public interface PromotionService {
    void create(ItemCreateRequest itemCreateRequest);

    void createAll(List<ItemCreateRequest> requests);
}
