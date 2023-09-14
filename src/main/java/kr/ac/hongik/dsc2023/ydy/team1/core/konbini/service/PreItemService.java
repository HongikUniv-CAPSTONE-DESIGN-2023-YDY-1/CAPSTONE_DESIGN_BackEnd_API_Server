package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PreItemSearchResponse;
import org.springframework.data.domain.Page;

public interface PreItemService {
    Page<PreItemSearchResponse> getAll(int page, int sizePerPage);
}
