package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import java.time.LocalDate;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.ItemCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ItemCreateRequest {
    private String name;
    private String imgName;
    private String brand;
    private String promotion;
    private int pricePerUnit;
    private LocalDate startDate;
    private LocalDate endDate;
    private ItemCategory category;
    private long preId;
}
