package kr.ac.hongik.dsc2023.ydy.team1.core.entity;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.ItemCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    @Type(type = "json")
    @Column(columnDefinition = "longtext")
    private Map<String, Object> subCategory = new HashMap<>();

    public Item(String name, String imgUrl, ItemCategory category) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.category = category;
    }
}
