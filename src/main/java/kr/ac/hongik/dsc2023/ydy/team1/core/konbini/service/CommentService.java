package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentUpdateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.comment.CommentResponse;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponse create(CommentCreateRequest commentCreateRequest, int userID);
    Page<CommentResponse> readAllByItemID(long itemId, int page, int sizePerPage);
    Page<CommentResponse> readAllByUserID(long userId, int page, int sizePerPage);
    Page<CommentResponse> readAllByPromotionID(long promotionID, int page, int sizePerPage);
    CommentResponse update(CommentUpdateRequest commentUpdateRequest, int userID);
    void delete(long commentID, int userID);
}