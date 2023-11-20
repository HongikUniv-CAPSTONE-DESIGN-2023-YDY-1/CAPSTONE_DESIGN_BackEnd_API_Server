package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KonbiniSearchItemResponse {
    private List<KonbiniSearchItem> searchItems;
}
