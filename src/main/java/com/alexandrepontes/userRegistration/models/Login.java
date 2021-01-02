package com.alexandrepontes.userRegistration.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TB_LOGIN")
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogin;

    private String nameLogin;
    private String password;

    public Login(){}

    public Login(String nameLogin, String password) {
        this.nameLogin = nameLogin;
        this.password = password;
    }

    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
