package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * Item 생성 요청을 표현하는 클래스
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "itemType",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "konbini", value = KonbiniItemCreateRequestDTO.class)
})
public abstract class ItemCreateRequestDTO {
    protected String name;
    protected MultipartFile picture;
}
