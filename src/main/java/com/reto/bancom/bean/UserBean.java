package com.reto.bancom.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {

    private String idUser;
    private String cellphone;
    private String name;
    private String lastname;
    private String password;
    private String creationDate;
    private String lastModificationDate;

}
