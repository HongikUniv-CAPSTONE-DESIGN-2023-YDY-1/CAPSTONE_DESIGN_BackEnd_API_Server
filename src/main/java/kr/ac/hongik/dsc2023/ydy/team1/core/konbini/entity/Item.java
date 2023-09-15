package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.ItemCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String subCategory;

    public Item(String name,String imgUrl, ItemCategory category) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.category = category;
    }
}
