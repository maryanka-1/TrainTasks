package com.example.catsgram.controller;

import com.example.catsgram.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private final List<Post> listPosts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post> getListPosts() {
        return listPosts;
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post) {
        listPosts.add(post);
        return post;
    }
}
