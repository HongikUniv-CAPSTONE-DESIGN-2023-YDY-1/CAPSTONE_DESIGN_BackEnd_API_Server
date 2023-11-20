package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.PreItemSearchResponse;
import org.springframework.data.domain.Page;

public interface PreItemService {
    Page<PreItemSearchResponse> getAll(int page, int sizePerPage);
}
