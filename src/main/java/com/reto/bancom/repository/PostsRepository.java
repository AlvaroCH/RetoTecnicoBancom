package com.reto.bancom.repository;

import com.reto.bancom.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, String> {

    Optional<Posts> findPostsByUser(String usuario);

    Optional<Posts> findPostsByLastModificationDate(String usuario, String lastModificationDate);

}
