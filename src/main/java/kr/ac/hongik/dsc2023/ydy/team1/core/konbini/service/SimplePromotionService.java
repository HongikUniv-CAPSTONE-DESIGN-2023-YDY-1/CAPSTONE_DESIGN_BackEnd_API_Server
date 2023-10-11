package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.ItemCategory;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPreItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimplePromotionService implements PromotionService {
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final KonbiniItemRepository itemRepository;
    private final KonbiniPreItemRepository preItemRepository;
    @Transactional
    @Override
    public void create(ItemCreateRequest request) {
        PromotionInfo promotionInfo = makePromotionInfo(request);
        promotionInfoRepository.save(promotionInfo);
        preItemRepository.deleteById(request.getPreId());
    }

    private PromotionInfo makePromotionInfo(ItemCreateRequest request) {
        String itemName = request.getName();
        String itemImage = request.getImgName();
        ItemCategory category = request.getCategory();
        Item item = itemRepository.findByName(itemName).orElse(new Item(itemName, itemImage, category));
        return PromotionInfo.builder()
                .promotion(KonbiniPromotion.valueOf(request.getPromotion()))
                .startDate(request.getStartDate())
                .brand(KonbiniBrand.valueOf(request.getBrand()))
                .endDate(request.getEndDate())
                .item(item)
                .price(request.getPricePerUnit())
                .build();
    }

    @Transactional
    @Override
    public void createAll(List<ItemCreateRequest> requests) {
        List<PromotionInfo> promotionInfos = requests.stream().map(this::makePromotionInfo).collect(Collectors.toList());
        promotionInfoRepository.saveAll(promotionInfos);
        List<Long> ids = promotionInfos.stream().map(PromotionInfo::getId).collect(Collectors.toList());
        preItemRepository.deleteAllByIdIn(ids);
    }
}
