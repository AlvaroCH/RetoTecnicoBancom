package com.reto.bancom.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsRequest {

    private String idPost;
    private String text;
    private String idUser;
    private String creationDate;
    private String lastModificationDate;

}
