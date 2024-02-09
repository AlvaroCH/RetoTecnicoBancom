package com.reto.bancom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPost")
    private String idPost;

    @Column(name = "text")
    private String text;

    @CreatedDate
    @Column(name = "creationDate")
    private String creationDate;

    @LastModifiedDate
    @Column(name = "lastModificationDate")
    private String lastModificationDate;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User usuario;

}
