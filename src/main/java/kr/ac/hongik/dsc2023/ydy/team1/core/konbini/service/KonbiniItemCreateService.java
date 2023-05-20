package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemCreateService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KonbiniItemCreateService<E extends KonbiniItemCreateRequestDTO, T extends ItemsCreateRequestDTO<E>> implements ItemCreateService<E, T> {
    private KonbiniPromotionInfoRepository promotionInfoRepository;
    private KonbiniItemRepository itemRepository;
    @Override
    public ItemCreateResponseDTO create(T requestDTO) {
        List<E> lists = requestDTO.getItemList();
        List<PromotionInfo> promotionInfos = new ArrayList<>();
        for (E createDto : lists) {
            String itemName = createDto.getName();
            KonbiniBrand brand = createDto.getBrand();
            Optional<PromotionInfo> searchResult = promotionInfoRepository.findByItem_NameAndBrand(itemName,brand);
            PromotionInfo promotionInfo;
            if (searchResult.isEmpty()){ // 최초 생성
                Item item = itemRepository.findByName(itemName).orElse(new Item(itemName));
                promotionInfo = createDto.toPromotionInfo(item);
            }else{
                promotionInfo = searchResult.get();
                promotionInfo.update(createDto);
            }
            promotionInfos.add(promotionInfo);
        }
        promotionInfoRepository.saveAll(promotionInfos);
        return new KonbiniItemCreateResponseDTO();
    }
}
