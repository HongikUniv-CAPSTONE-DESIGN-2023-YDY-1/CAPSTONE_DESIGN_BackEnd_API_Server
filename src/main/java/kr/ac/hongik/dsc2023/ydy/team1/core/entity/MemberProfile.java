package kr.ac.hongik.dsc2023.ydy.team1.core.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.PersonalizeAlg;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
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
    private Map<String, Object> recommendData;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    private PersonalizeAlg personalizeAlg;

    public MemberProfile(Member member) {
        this.member = member;
        this.recommendData = new HashMap<>();
        int random = new Random().nextInt(1000) % 3;
        this.personalizeAlg = PersonalizeAlg.values()[random];
    }

    public void update(Map<String, Object> recommendData) {
        this.recommendData = recommendData;
    }

    public long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Map<String, Object> getRecommendData() {
        return recommendData;
    }
}
