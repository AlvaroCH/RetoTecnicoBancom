package com.reto.bancom.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWebResponse {

    private String idUser;
    private String cellphone;
    private String name;
    private String lastname;
    private String password;
    private String creationDate;
    private String lastModificationDate;

}
