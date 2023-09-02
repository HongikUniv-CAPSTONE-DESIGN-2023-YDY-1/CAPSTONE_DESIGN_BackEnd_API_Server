package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> getCommentsByPromotionInfo_Item_Id(long itemId, Pageable pageable);
    Page<Comment> getCommentsByMember_Id(long memberID, Pageable pageable);
    Page<Comment> getCommentsByPromotionInfo_Id(long promotionID, Pageable pageable);
}
