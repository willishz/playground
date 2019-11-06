package org.willishz.playground.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String passwd;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    @Column
    private Date regTime;

    public User(String email, String nickname, String passwd, String username, Date regTime) {
        super();
        this.email = email;
        this.nickname = nickname;
        this.passwd = passwd;
        this.username = username;
        this.regTime = regTime;
    }
}