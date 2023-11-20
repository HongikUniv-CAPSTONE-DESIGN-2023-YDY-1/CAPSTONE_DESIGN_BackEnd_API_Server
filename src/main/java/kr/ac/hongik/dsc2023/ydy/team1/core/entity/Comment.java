package kr.ac.hongik.dsc2023.ydy.team1.core.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.comment.CommentUpdateRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        name = "comment",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"member_id", "promotion_id"}
                )
        }
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    private PromotionInfo promotionInfo;

    @Column(nullable = false)
    private int star;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime writeDate = LocalDateTime.now();


    private LocalDateTime updatedDate;

    public void update(CommentUpdateRequest updateRequest) {
        star = updateRequest.getStar();
        content = updateRequest.getContent();
        updatedDate = LocalDateTime.now();
    }
}
