package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.PreItemSearchResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PreItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniPreItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KonbiniPreItemService implements PreItemService {
    private final KonbiniPreItemRepository preItemRepository;

    @Override
    public Page<PreItemSearchResponse> getAll(int page, int sizePerPage) {
        Page<PreItem> all = preItemRepository.findAll(PageRequest.of(page, sizePerPage));
        return all.map(PreItemSearchResponse::fromEntity);
    }
}
