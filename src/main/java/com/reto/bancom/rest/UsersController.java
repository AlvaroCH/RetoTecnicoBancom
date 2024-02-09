package com.reto.bancom.rest;

import com.reto.bancom.bean.UserBean;
import com.reto.bancom.bean.UserRequest;
import com.reto.bancom.response.BaseWebResponse;
import com.reto.bancom.service.UserService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String home() {	return "Hello World!";}


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/getPosts")
    public Single<UserBean> getExchangeRateBean(@RequestParam(value = "id") String id,
                                                @RequestParam(value = "lastModification") String lastModify) {
        return userService.getUsers(id, lastModify)
                .subscribeOn(Schedulers.io());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse>> updateExchangeRate(@RequestBody UserRequest userRequest) {
        return userService.updateUser(userRequest).subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }

}
