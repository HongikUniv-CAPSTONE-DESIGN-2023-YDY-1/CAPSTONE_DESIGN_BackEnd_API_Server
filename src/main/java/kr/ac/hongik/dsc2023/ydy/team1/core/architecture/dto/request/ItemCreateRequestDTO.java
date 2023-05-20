package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * Item 생성 요청을 표현하는 클래스
 */
@SuperBuilder
@ToString
@Getter
public abstract class ItemCreateRequestDTO {
    protected String name;
    protected MultipartFile picture;
}
