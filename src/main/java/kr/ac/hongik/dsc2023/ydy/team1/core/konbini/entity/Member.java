package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true,length = 100)
    private String email;
    @Column(nullable = false, length = 256)
    private String password;

    public void updatePassword(String password){
        this.password = password;
    }
}
