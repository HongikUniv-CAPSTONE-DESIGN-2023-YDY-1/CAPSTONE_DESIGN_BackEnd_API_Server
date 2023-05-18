package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemSearchService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItemResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class KonbiniItemSearchService<E extends SearchItem,T extends SearchItemRequestDTO> implements ItemSearchService<E,T> {
    private KonbiniPromotionInfoRepository promotionInfoRepository;
    @Override
    public SearchItemResponseDTO<E> search(T requestDTO) {
        List<PromotionInfo> promotionInfos = promotionInfoRepository.findAllByItem_NameContains(requestDTO.getName());
        List<E> searchItems = new ArrayList<>();
        for (PromotionInfo promotionInfo : promotionInfos) {
            E konbiniSearchItem = (E) new KonbiniSearchItem(promotionInfo);
            searchItems.add(konbiniSearchItem);
        }
        return KonbiniSearchItemResponseDTO.<E>builder().searchItems(searchItems).build();
    }
}
