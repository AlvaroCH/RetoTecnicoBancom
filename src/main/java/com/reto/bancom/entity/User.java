package com.reto.bancom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private String idUser;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastname;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "creationDate")
    private String creationDate;

    @LastModifiedDate
    @Column(name = "lastModificationDate")
    private String lastModificationDate;
}
