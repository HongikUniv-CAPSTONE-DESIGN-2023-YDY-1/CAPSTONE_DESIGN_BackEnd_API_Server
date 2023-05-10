package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.konbini;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.port.ResponseWrapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class KonbiniResponseWrapper<T> extends ResponseWrapper<T> {
    private String message;
}
