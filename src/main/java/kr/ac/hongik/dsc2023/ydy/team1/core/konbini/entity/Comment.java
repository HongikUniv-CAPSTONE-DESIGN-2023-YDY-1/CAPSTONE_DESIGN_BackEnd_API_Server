package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentUpdateRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        name="comment",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"member_id", "promotion_id"}
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

    public void update(CommentUpdateRequest updateRequest){
        star = updateRequest.getStar();
        content = updateRequest.getContent();
        updatedDate = LocalDateTime.now();
    }
}
