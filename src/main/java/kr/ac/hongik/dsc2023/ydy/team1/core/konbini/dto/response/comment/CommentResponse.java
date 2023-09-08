package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.comment;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Getter
@Builder
public class CommentResponse {
    private long commentId;
    private long promotionId;
    @Max(5)
    @Min(1)
    private int star;
    private String content;

    public static CommentResponse fromEntity(Comment comment){
        return CommentResponse.builder()
                .commentId(comment.getId())
                .promotionId(comment.getPromotionInfo().getId())
                .content(comment.getContent())
                .star(comment.getStar())
                .build();
    }
}
