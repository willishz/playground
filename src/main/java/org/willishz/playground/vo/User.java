package org.willishz.playground.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    private Long id;
    private String username;
    private String passwd;
    private String email;
    private String nickname;
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