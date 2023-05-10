package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;
}
