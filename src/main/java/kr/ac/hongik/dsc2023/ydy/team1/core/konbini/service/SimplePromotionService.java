package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPreItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SimplePromotionService implements PromotionService {
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final KonbiniItemRepository itemRepository;
    private final KonbiniPreItemRepository preItemRepository;
    @Transactional
    @Override
    public void create(ItemCreateRequest request) {
        String itemName = request.getName();
        String itemImage = request.getImgName();
        Item item = itemRepository.findByName(itemName).orElse(new Item(itemName, itemImage));
        PromotionInfo promotionInfo = PromotionInfo.builder()
                .promotion(KonbiniPromotion.valueOf(request.getPromotion()))
                .startDate(request.getStartDate())
                .brand(KonbiniBrand.valueOf(request.getBrand()))
                .endDate(request.getEndDate())
                .item(item)
                .price(request.getPricePerUnit())
                .build();
        promotionInfoRepository.save(promotionInfo);
        preItemRepository.deleteById(request.getPreId());
    }
}
