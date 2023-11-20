package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniSearchItemRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItemResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class KonbiniItemSearchService {
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    @Value("${ai.server-path}")
    private String AI_SERVER_PATH;

    public KonbiniItemSearchService(KonbiniPromotionInfoRepository promotionInfoRepository) {
        this.promotionInfoRepository = promotionInfoRepository;
    }

    public SearchItemResponse<KonbiniSearchItem> search(KonbiniSearchItemRequest requestDTO) {
        String itemName = requestDTO.getName();
        return searchFromRepository(itemName);
    }

    private KonbiniSearchItemResponse<KonbiniSearchItem> searchFromRepository(String itemName) {
        List<PromotionInfo> promotionInfos = promotionInfoRepository.findAllByItem_NameContainsAndStartDateGreaterThanEqualAndEndDateGreaterThanEqual(
                itemName, LocalDate.now(), LocalDate.now());
        List<KonbiniSearchItem> searchItems = promotionInfos.stream()
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
        return KonbiniSearchItemResponse.<KonbiniSearchItem>builder().searchItems(searchItems).build();
    }

    public SearchItemResponse<KonbiniSearchItem> searchByImage(MultipartFile img) {
        String itemName = requestToAIModule(img);
        return searchFromRepository(itemName);
    }

    private String requestToAIModule(MultipartFile img) {

        RestTemplate restTemplate = new RestTemplate();
        String url = AI_SERVER_PATH + "/hello";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("multipart/form-data;charset=UTF-8"));
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("img", img.getResource());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> aiResponse = objectMapper.readValue(response.getBody(),
                    new TypeReference<Map<String, String>>() {
                    });
            String name = new String(Base64.getDecoder().decode(aiResponse.get("item")));
            name = name.split("_")[0];
            log.info(name);
            return name;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
