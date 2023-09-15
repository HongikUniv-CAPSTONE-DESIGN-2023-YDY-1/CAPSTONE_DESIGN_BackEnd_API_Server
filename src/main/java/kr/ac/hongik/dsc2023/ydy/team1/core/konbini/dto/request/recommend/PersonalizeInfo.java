package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.recommend;

import lombok.*;

import java.util.Arrays;

import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.recommend.PersonalizeInfo.RecommendType.COMMENT_READ;
import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.recommend.PersonalizeInfo.RecommendType.COMMENT_WRITE;

/**
 * 추천 시스템의 구현을 위한 추천을 표현하는 DTO. 컨트롤러가 아니라 서비스가 생성한다.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PersonalizeInfo {
    private int memberID;
    private RecommendType recommendType;
    private long promotionID;
    @RequiredArgsConstructor
    public enum RecommendType{
        COMMENT_READ("리뷰 조회"),COMMENT_WRITE("리뷰 작성");
        private final String label;
        public String getLabel() {
            return label;
        }

        public static RecommendType fromValue(String value) {
            return Arrays.stream(values())
                    .filter(itemCategory -> itemCategory.label.equals(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid RecommendType: " + value));
        }
    }

    public static PersonalizeInfo makeCommentRead(int memberID, long promotionID){
        PersonalizeInfo personalizeInfo = new PersonalizeInfo();
        personalizeInfo.memberID = memberID;
        personalizeInfo.promotionID = promotionID;
        personalizeInfo.recommendType = COMMENT_READ;
        return personalizeInfo;
    }

    public static PersonalizeInfo makeCommentWrite(int memberID, long promotionID){
        PersonalizeInfo personalizeInfo = new PersonalizeInfo();
        personalizeInfo.memberID = memberID;
        personalizeInfo.promotionID = promotionID;
        personalizeInfo.recommendType = COMMENT_WRITE;
        return personalizeInfo;
    }
}
