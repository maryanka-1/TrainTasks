package com.example.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class User {
    @NonNull
    private String nickname;
    @NonNull
    private LocalDate birthdate;
    @NonNull
    private String email;

}
