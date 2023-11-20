package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.comment.CommentUpdateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.comment.CommentResponse;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponse create(CommentCreateRequest commentCreateRequest, int userID);

    Page<CommentResponse> readAllByItemID(long itemId, int page, int sizePerPage);

    Page<CommentResponse> readAllByUserID(int userId, int page, int sizePerPage);

    Page<CommentResponse> readAllByPromotionID(int userID, long promotionID, int page, int sizePerPage);

    CommentResponse update(CommentUpdateRequest commentUpdateRequest, int userID);

    void delete(long commentID, int userID);
}
