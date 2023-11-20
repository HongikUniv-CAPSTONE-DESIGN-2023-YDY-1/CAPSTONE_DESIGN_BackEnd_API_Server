package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 각종 API의 응답을 감싸는 랩퍼 클래스
 *
 * @param <T>
 */
@Builder
@Getter
public class Response<T> {
    private T data;
    private String message;
}
