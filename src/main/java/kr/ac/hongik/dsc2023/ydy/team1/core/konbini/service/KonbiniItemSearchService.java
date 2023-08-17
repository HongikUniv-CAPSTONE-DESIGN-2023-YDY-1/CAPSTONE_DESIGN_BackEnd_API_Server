package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemSearchService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItemResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class KonbiniItemSearchService<E extends SearchItem,T extends SearchItemRequest> implements ItemSearchService<E,T> {
    @Value("${ai.server-path}")
    private String AI_SERVER_PATH;
    private KonbiniPromotionInfoRepository promotionInfoRepository;

    public KonbiniItemSearchService(KonbiniPromotionInfoRepository promotionInfoRepository) {
        this.promotionInfoRepository = promotionInfoRepository;
    }

    @Override
    public SearchItemResponse<E> search(T requestDTO) {
        String itemName = requestDTO.getName();
        return searchFromRepository(itemName);
    }
    private KonbiniSearchItemResponse<E> searchFromRepository(String itemName){
        List<PromotionInfo> promotionInfos = promotionInfoRepository.findAllByItem_NameContains(itemName);
        List<E> searchItems = new ArrayList<>();
        for (PromotionInfo promotionInfo : promotionInfos) {
            E konbiniSearchItem = (E) new KonbiniSearchItem(promotionInfo);
            searchItems.add(konbiniSearchItem);
        }
        return KonbiniSearchItemResponse.<E>builder().searchItems(searchItems).build();
    }

    @Override
    public SearchItemResponse<E> searchByImage(MultipartFile img) {
        String itemName = requestToAIModule(img);
        return searchFromRepository(itemName);
    }
    private String requestToAIModule(MultipartFile img){

        RestTemplate restTemplate = new RestTemplate();
        String url = AI_SERVER_PATH+"/hello";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("multipart/form-data;charset=UTF-8"));
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("img", img.getResource());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String,String> aiResponse = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, String>>() {});
            String name = new String(Base64.getDecoder().decode(aiResponse.get("item")));
            name = name.split("_")[1];
            log.info(name);
            return name;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
