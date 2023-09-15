package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AccessLevel;
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
}
