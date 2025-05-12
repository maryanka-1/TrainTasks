package com.example.catsgram.repository;

import com.example.catsgram.exceptions.PostNotFoundException;
import com.example.catsgram.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> listPosts = new ArrayList<>();


    public List<Post> getListPosts() {
        return listPosts;
    }

    public Post addPost(Post post) {
        listPosts.add(post);
        return post;
    }

    public Post findPostByID(int id) {
        return listPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->new PostNotFoundException("Пост c id " + id + " не найден")); //добавить exception

    }
}
