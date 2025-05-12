package com.example.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Post {
    private Integer id;
    @NonNull
    private final String author;
    private final LocalDate creationDate = LocalDate.now();
    @NonNull
    private String photoUrl;
    private String description;

}
