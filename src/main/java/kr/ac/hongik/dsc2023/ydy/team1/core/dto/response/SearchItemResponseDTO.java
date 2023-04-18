package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.Brand;
import lombok.Getter;

import java.util.List;
import java.util.Set;
@Getter
/**
 * 상품 검색 결과를 표현하는 클래스
 */
public class SearchItemResponseDTO {
    private List<SearchItem> searchItems;
    private static class SearchItem{
        String name;
        Set<Brand> promotionBrands;
        List<StoreResponse> nearStores;
        int price;
        int pricePerUnit;
        private static class StoreResponse{
            String name;
            double latitude;
            double longitude;
        }
    }
}
