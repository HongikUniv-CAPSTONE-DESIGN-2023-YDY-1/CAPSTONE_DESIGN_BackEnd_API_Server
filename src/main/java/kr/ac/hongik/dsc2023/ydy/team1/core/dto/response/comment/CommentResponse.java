package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.comment;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    private long commentId;
    private long promotionId;
    @Max(5)
    @Min(1)
    private int star;
    private String content;

    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .promotionId(comment.getPromotionInfo().getId())
                .content(comment.getContent())
                .star(comment.getStar())
                .build();
    }
}
