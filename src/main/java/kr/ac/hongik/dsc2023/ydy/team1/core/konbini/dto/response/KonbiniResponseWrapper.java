package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ResponseWrapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class KonbiniResponseWrapper<T> extends ResponseWrapper<T> {
    private String message;
}
