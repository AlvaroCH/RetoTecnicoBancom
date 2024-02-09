package com.reto.bancom.rest;

import com.reto.bancom.bean.PostsBean;
import com.reto.bancom.bean.PostsRequest;
import com.reto.bancom.response.BaseWebResponse;
import com.reto.bancom.service.PostService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    String home() {	return "Hello World!";}

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/getPosts")
    public Single<PostsBean> getExchangeRateBean(@RequestParam(value = "id") String id,
                                                 @RequestParam(value = "lastModification") String lastModify) {
        return postService.getPosts(id, lastModify)
                .subscribeOn(Schedulers.io());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse>> updateExchangeRate(@RequestBody PostsRequest postsRequest) {
        return postService.updatePosts(postsRequest).subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }
}
