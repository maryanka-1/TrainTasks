package com.example.catsgram.service;

import com.example.catsgram.exceptions.UserNotFoundException;
import com.example.catsgram.model.Feed;
import com.example.catsgram.model.Post;
import com.example.catsgram.model.User;
import com.example.catsgram.repository.PostRepository;
import com.example.catsgram.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private static int id = 0;
    private final PostRepository repository;
    private final UserService userService;

    public List<Post> getPosts() {
        return repository.getListPosts();
    }

    public Post createPost(Post post) {
        User owner = userService.findUserByNick(post.getAuthor());
        if (owner == null) {
            throw new UserNotFoundException("пользователь не существует");
        }
        post.setId(id++);
        return repository.addPost(post);
    }

    public Post findPostById(int id) {
        return repository.findPostByID(id);
    }

    public List<Post> findAll(String typeSort, int size, int page) {

        List<Post> postList = repository.getListPosts();
        return postList.stream()
                .sorted((post1, post2) -> {
                    int i = post1.getCreationDate().compareTo(post2.getCreationDate());
                    if (typeSort.equals("desc")) {
                        i = i * -1;
                    }
                    return i;
                })
                .skip((long) page * size)
                .limit(size)
                .toList();
    }

    public List<Post> searchPosts(String author, LocalDate date) {
        return repository.getListPosts().stream()
                .filter(post -> (author.equals("all") || post.getAuthor().equals(author)) &&
                        (date.isEqual(LocalDate.of(1900, 01, 01)) || post.getCreationDate().isEqual(date)))
                .toList();
    }

    public List<Post> getFeed(Feed feed){
        List<Post> allPostsFriends = new ArrayList<>();
        for(String string: feed.getFriends()){
            String name = userService.findUserByEmail(string).getNickname();
            repository.getListPosts().stream()
                    .filter(post -> post.getAuthor().equals(name))
                    .forEach(allPostsFriends::add);
        }
        return allPostsFriends.stream()
                .sorted((post1, post2) -> {
                    int i = post1.getCreationDate().compareTo(post2.getCreationDate());
                    if (feed.getSort().equals("desc")) {
                        i = i * -1;
                    }
                    return i;
                })
                .limit(feed.getSize())
                .toList();
    }
}
