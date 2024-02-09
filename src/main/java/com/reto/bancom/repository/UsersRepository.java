package com.reto.bancom.repository;

import com.reto.bancom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    Optional<User> findUserByIdUser(String id);

    Optional<User> findUserByLastModificationDate(String usuario, String lastModificationDate);
}
