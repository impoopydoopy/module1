package com.example.module_1.controller.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@RequiredArgsConstructor
public class GameDTO {
    @Id
    public String id;
    public String name;
    public String genre;
    public String description;

}
