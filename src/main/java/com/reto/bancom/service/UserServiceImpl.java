package com.reto.bancom.service;

import com.reto.bancom.bean.UserBean;
import com.reto.bancom.bean.UserRequest;
import com.reto.bancom.entity.User;
import com.reto.bancom.repository.UsersRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Single<UserBean> getUsers(String id, String lastModification) {

    return Single.create(singleSubscriber -> {
        Optional<User> optionalExchangeRate = userRepository
                .findUserByLastModificationDate(id, lastModification);
        if (!optionalExchangeRate.isPresent())
            singleSubscriber.onError(new EntityNotFoundException("Not data found..."));
    });
    }

    @Override
    public Completable saveUser(UserRequest request) {
    return Completable.create(completableSubscriber -> {

        User user = new User();
        user.setIdUser(request.getIdUser());
        user.setCellphone(request.getCellphone());
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setCreationDate(request.getCreationDate());
        user.setLastModificationDate(request.getLastModificationDate());
        userRepository.save(user);
        completableSubscriber.onComplete();
    });
    }

    @Override
    public Completable updateUser(UserRequest request) {
    return Completable.create(completableSubscriber -> {

        Optional<User> optionalExchangeRate = userRepository
                .findUserByLastModificationDate(request.getIdUser(), request.getLastModificationDate());
        if (!optionalExchangeRate.isPresent())
            completableSubscriber.onError(new EntityNotFoundException("No data found"));
        else {
            User user = optionalExchangeRate.get();
            user.setIdUser(request.getIdUser());
            user.setCellphone(request.getCellphone());
            user.setName(request.getName());
            user.setLastname(request.getLastname());
            user.setCreationDate(request.getCreationDate());
            user.setLastModificationDate(request.getLastModificationDate());
            userRepository.save(user);
            completableSubscriber.onComplete();
        }
    });
    }
}
