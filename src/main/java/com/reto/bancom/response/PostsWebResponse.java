package com.reto.bancom.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostsWebResponse {

    private String idPost;
    private String text;
    private String idUser;
    private String creationDate;
    private String lastModificationDate;

}
