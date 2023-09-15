package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Entity
@TypeDef(name = "json",typeClass = JsonType.class)
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
}
