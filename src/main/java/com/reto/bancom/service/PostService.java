package com.reto.bancom.service;

import com.reto.bancom.bean.PostsBean;
import com.reto.bancom.bean.PostsRequest;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface PostService {

    Single<PostsBean> getPosts(String id, String lastModification);

    Completable savePosts(PostsRequest request);

    Completable updatePosts(PostsRequest request);
}
