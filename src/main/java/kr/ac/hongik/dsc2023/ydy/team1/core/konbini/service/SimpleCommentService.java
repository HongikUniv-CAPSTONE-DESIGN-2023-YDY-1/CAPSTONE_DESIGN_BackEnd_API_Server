package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentUpdateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.comment.CommentResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Comment;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.CommentRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class SimpleCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    @Transactional
    @Override
    public CommentResponse create(CommentCreateRequest commentCreateRequest, int userID) {
        Comment comment = makeComment(commentCreateRequest, userID);
        validatePromotionIsOnGoing(comment.getPromotionInfo());
        commentRepository.saveAndFlush(comment);
        return CommentResponse.fromEntity(comment);
    }
    private Comment makeComment(CommentCreateRequest request, int userID){
        Member member = memberRepository.findById(userID).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        PromotionInfo promotionInfo = promotionInfoRepository.findById(request.getPromotionId())
                .orElseThrow(() -> new NoSuchElementException("없는 행사입니다."));
        return Comment.builder()
                .content(request.getContent())
                .star(request.getStar())
                .promotionInfo(promotionInfo)
                .member(member).build();
    }

    private void validatePromotionIsOnGoing(PromotionInfo promotionInfo){
        LocalDate start = promotionInfo.getStartDate().minusDays(1);
        LocalDate end = promotionInfo.getEndDate().plusDays(1);
        LocalDate now = LocalDate.now();
        if (!start.isBefore(now) || !end.isAfter(now)) {
            throw new IllegalStateException("종료된 행사에는 리뷰를 작성할 수 없습니다.");
        }
    }
    @Transactional
    @Override
    public Page<CommentResponse> readAllByItemID(long itemId, int page, int sizePerPage) {
        return commentRepository.getCommentsByPromotionInfo_Item_Id(itemId, PageRequest.of(page, sizePerPage)).map(CommentResponse::fromEntity);
    }
    @Transactional
    @Override
    public Page<CommentResponse> readAllByUserID(int userId, int page, int sizePerPage) {
        return commentRepository.getCommentsByMember_Id(userId,PageRequest.of(page,sizePerPage)).map(CommentResponse::fromEntity);
    }
    @Transactional
    @Override
    public Page<CommentResponse> readAllByPromotionID(long promotionID, int page, int sizePerPage) {
        return commentRepository.getCommentsByPromotionInfo_Id(promotionID,PageRequest.of(page,sizePerPage)).map(CommentResponse::fromEntity);
    }
    @Transactional
    @Override
    public CommentResponse update(CommentUpdateRequest commentUpdateRequest, int userID) {
        Comment comment = commentRepository.findById(commentUpdateRequest.getCommentId())
                .orElseThrow(() -> new NoSuchElementException("없는 리뷰 입니다."));
        validateCommentUpdateRequest(comment, userID);
        validatePromotionIsOnGoing(comment.getPromotionInfo());
        comment.update(commentUpdateRequest);
        commentRepository.saveAndFlush(comment);
        return CommentResponse.fromEntity(comment);
    }
    private void validateCommentUpdateRequest(Comment comment, int userID){
        if(comment.getMember().getId() != userID){
            throw new SecurityException("자신이 작성한 리뷰만 수정할 수 있습니다.");
        }
    }
    @Transactional
    @Override
    public void delete(long commentID, int userID) {
        Comment comment = commentRepository.findById(commentID).orElseThrow(() -> new NoSuchElementException("없는 리뷰입니다."));
        validateCommentDeleteRequest(comment, userID);
        validatePromotionIsOnGoing(comment.getPromotionInfo());
        commentRepository.delete(comment);
    }
    private void validateCommentDeleteRequest(Comment comment, int userID){
        if(comment.getMember().getId() != userID){
            throw new SecurityException("자신이 작성한 리뷰만 삭제할 수 있습니다.");
        }
    }
}
