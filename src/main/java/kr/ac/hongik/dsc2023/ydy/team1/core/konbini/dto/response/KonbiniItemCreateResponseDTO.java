package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class KonbiniItemCreateResponseDTO<E> extends ItemCreateResponseDTO {
    private List<E> failList;
}
