package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.comment;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CommentUpdateRequest {
    private long commentId;
    @Max(5)
    @Min(1)
    private int star;
    private String content;

    public static CommentUpdateRequest getInstance() {
        return new CommentUpdateRequest(1, 3, "testUpdatedComment");
    }
}
