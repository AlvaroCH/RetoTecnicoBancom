package com.reto.bancom.service;

import com.reto.bancom.bean.PostsBean;
import com.reto.bancom.bean.PostsRequest;
import com.reto.bancom.entity.Posts;
import com.reto.bancom.repository.PostsRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class PostServiceImpl implements PostService{

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Single<PostsBean> getPosts(String id, String lastModification) {

    return Single.create(singleSubscriber -> {
        Optional<Posts> optionalExchangeRate = postsRepository
                .findPostsByLastModificationDate(id, lastModification);
        if (!optionalExchangeRate.isPresent())
            singleSubscriber.onError(new EntityNotFoundException("Not data found..."));
    });
    }

    @Override
    public Completable savePosts(PostsRequest request) {

    return Completable.create(completableSubscriber -> {

        Posts posts = new Posts();
        posts.setIdPost(request.getIdPost());
        posts.setText(request.getText());
        posts.setCreationDate(request.getCreationDate());
        posts.setLastModificationDate(request.getLastModificationDate());
        postsRepository.save(posts);
        completableSubscriber.onComplete();

    });
    }

    @Override
    public Completable updatePosts(PostsRequest request) {

    return Completable.create(completableSubscriber -> {

        Optional<Posts> optionalExchangeRate = postsRepository
                .findPostsByLastModificationDate(request.getIdUser(), request.getLastModificationDate());
        if (!optionalExchangeRate.isPresent())
            completableSubscriber.onError(new EntityNotFoundException("No data found"));
        else {
            Posts posts = optionalExchangeRate.get();
            posts.setIdPost(request.getIdPost());
            posts.setText(request.getText());
            posts.setCreationDate(request.getCreationDate());
            posts.setLastModificationDate(request.getLastModificationDate());
            postsRepository.save(posts);
            completableSubscriber.onComplete();
        }
    });
    }
}
