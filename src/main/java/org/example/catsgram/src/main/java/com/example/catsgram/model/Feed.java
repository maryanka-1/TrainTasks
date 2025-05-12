package com.example.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Feed {
    private String sort;
    private int size;
    private List<String> friends;
}
