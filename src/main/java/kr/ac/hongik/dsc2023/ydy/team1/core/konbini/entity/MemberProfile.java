package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@TypeDef(name = "json",typeClass = JsonType.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true)
    private Member member;
    @Type(type = "json")
    @Column(columnDefinition = "longtext")
    private Map<String,Object> recommendData;

    public MemberProfile(Member member){
        this.member = member;
        this.recommendData = new HashMap<>();
    }

    public void update(Map<String,Object> recommendData){
        this.recommendData = recommendData;
    }

    public long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    /**
     * 다음과 같은 포맷의 json임.
     * <pre>
     * {
     *   "BEVERAGE": 5,
     *   "SNACK": 3,
     *   "FOOD": 4,
     *   "ICE_CREAM": 2,
     *   "HOUSEHOLD": 1,
     *   "recent_items": [
     *     {
     *       "item_name": "커피",
     *       "access_time": "2023-09-17 10:30:00"
     *     },
     *     {
     *       "item_name": "과자",
     *       "access_time": "2023-09-16 15:45:00"
     *     },
     *     {
     *       "item_name": "라면",
     *       "access_time": "2023-09-15 18:20:00"
     *     },
     *     {
     *       "item_name": "아이스크림",
     *       "access_time": "2023-09-14 09:00:00"
     *     },
     *     {
     *       "item_name": "세제",
     *       "access_time": "2023-09-13 14:10:00"
     *     }
     *   ]
     * }
     * </pre>
     * @return 위 json에 대응되는 Map 객체.
     */
    public Map<String, Object> getRecommendData() {
        return recommendData;
    }
}
