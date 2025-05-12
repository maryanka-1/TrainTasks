package com.example.catsgram.controller;

import com.example.catsgram.exceptions.IncorrectParamException;
import com.example.catsgram.model.Feed;
import com.example.catsgram.model.Post;
import com.example.catsgram.model.User;
import com.example.catsgram.repository.PostRepository;
import com.example.catsgram.service.PostService;
import com.example.catsgram.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
//@Slf4j
public class PostController {
    private final PostService postService;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/posts")
    public List<Post> getListPosts(@RequestParam(value = "sort", defaultValue = "desc", required = false) String typeSort,
                                   @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                   @RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        if (!(page > 0)) {
            throw new IncorrectParamException("Page");
        }
        if (!(size > 0)) {
            throw new IncorrectParamException("Size");
        }
        List<Post> listPosts = postService.findAll(typeSort, size, page);
        log.debug("Посты по параметрам = {}", listPosts.stream().toList());
        return listPosts;
    }


    @GetMapping("/post/{postId}")
    public Post getPost(@PathVariable("postId") Integer id) {
        Post result = postService.findPostById(id);
        log.info("Пост id: {} - {}", id, result.toString());
        return result;
    }

    @GetMapping("/posts/search")
    public List<Post> searchPosts(@RequestParam(value = "author", defaultValue = "all", required = false) String author,
                                  @RequestParam(value = "date", defaultValue = "1900-01-01", required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Post> listPosts = postService.searchPosts(author, date);
        log.info("Посты по фильтрам");
        return listPosts;
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        log.debug("Пост {} был создан", newPost.toString());
        return newPost;
    }

    @PostMapping("/feed/friends")
    public List<Post> getPostFriends(@RequestBody String string) throws JsonProcessingException {
        Feed feed = objectMapper.readValue(string, Feed.class);
        return postService.getFeed(feed);
    }
}
