package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.port;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * Item 생성 요청을 표현하는 클래스
 */
@SuperBuilder
@ToString
public abstract class ItemCreateRequestDTO {
    protected String name;
    protected MultipartFile picture;
}
