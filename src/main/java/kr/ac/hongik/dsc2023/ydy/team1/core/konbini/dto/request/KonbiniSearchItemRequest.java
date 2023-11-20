package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchStrength;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KonbiniSearchItemRequest {
    private String name;
    private SearchStrength strength;
}