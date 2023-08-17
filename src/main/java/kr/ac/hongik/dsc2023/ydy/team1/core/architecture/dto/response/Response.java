package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 각종 API의 응답을 감싸는 랩퍼 클래스
 * @param <T>
 */
@SuperBuilder
@Getter
public abstract class Response<T>{
    protected T data;
}
