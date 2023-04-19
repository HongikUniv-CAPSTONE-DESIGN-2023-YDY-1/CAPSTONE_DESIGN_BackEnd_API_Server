package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response;

/**
 * 각종 API의 응답을 감싸는 랩퍼 클래스
 * @param <T>
 */
public abstract class ResponseWrapper<T>{
    protected T response;
}
