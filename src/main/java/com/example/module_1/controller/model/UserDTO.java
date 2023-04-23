package com.example.module_1.controller.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    @Id
    public String id;
    public String nickname;
    public String profileDescription;
    private List<String> gameIds; //game ids
    public List<String> achievements;

}
