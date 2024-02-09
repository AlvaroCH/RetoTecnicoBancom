package com.reto.bancom.service;

import com.reto.bancom.bean.UserBean;
import com.reto.bancom.bean.UserRequest;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserService {

    Single<UserBean> getUsers(String id, String lastModification);

    Completable saveUser(UserRequest request);

    Completable updateUser(UserRequest request);
}
