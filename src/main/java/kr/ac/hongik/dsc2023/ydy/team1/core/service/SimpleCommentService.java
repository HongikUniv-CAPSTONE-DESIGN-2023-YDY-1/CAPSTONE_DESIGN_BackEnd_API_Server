package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.comment.CommentUpdateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.comment.CommentResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Comment;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.event.PersonalizeEvent;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.CommentRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SimpleCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public CommentResponse create(CommentCreateRequest commentCreateRequest, int userID) {
        Comment comment = makeComment(commentCreateRequest, userID);
        validatePromotionIsOnGoing(comment.getPromotionInfo());
        try {
            commentRepository.saveAndFlush(comment);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("이미 리뷰를 작성했습니다.");
        }
        PersonalizeEvent personalizeEvent = PersonalizeEvent.makeCommentWrite(userID,
                commentCreateRequest.getPromotionId());
        eventPublisher.publishEvent(personalizeEvent);
        return CommentResponse.fromEntity(comment);
    }

    private Comment makeComment(CommentCreateRequest request, int userID) {
        Member member = memberRepository.findById(userID).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        PromotionInfo promotionInfo = promotionInfoRepository.findById(request.getPromotionId())
                .orElseThrow(() -> new NoSuchElementException("없는 행사입니다."));
        return Comment.builder()
                .content(request.getContent())
                .star(request.getStar())
                .promotionInfo(promotionInfo)
                .member(member).build();
    }

    private void validatePromotionIsOnGoing(PromotionInfo promotionInfo) {
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
        return commentRepository.getCommentsByPromotionInfo_Item_Id(itemId, PageRequest.of(page, sizePerPage))
                .map(CommentResponse::fromEntity);
    }

    @Transactional
    @Override
    public Page<CommentResponse> readAllByUserID(int userId, int page, int sizePerPage) {
        return commentRepository.getCommentsByMember_Id(userId, PageRequest.of(page, sizePerPage))
                .map(CommentResponse::fromEntity);
    }

    @Transactional
    @Override
    public Page<CommentResponse> readAllByPromotionID(int userID, long promotionID, int page, int sizePerPage) {
        PersonalizeEvent personalizeEvent = PersonalizeEvent.makeCommentRead(userID, promotionID);
        eventPublisher.publishEvent(personalizeEvent);
        return commentRepository.getCommentsByPromotionInfo_Id(promotionID, PageRequest.of(page, sizePerPage))
                .map(CommentResponse::fromEntity);
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

    private void validateCommentUpdateRequest(Comment comment, int userID) {
        if (comment.getMember().getId() != userID) {
            throw new SecurityException("자신이 작성한 리뷰만 수정할 수 있습니다.");
        }
    }

    @Transactional
    @Override
    public void delete(long commentID, int userID) {
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new NoSuchElementException("없는 리뷰입니다."));
        validateCommentDeleteRequest(comment, userID);
        validatePromotionIsOnGoing(comment.getPromotionInfo());
        commentRepository.delete(comment);
    }

    private void validateCommentDeleteRequest(Comment comment, int userID) {
        if (comment.getMember().getId() != userID) {
            throw new SecurityException("자신이 작성한 리뷰만 삭제할 수 있습니다.");
        }
    }
}
