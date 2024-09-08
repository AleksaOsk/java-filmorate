package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Data
public class Film {
    int id;
    String name;
    String description;
    LocalDate releaseDate;
    Integer duration;
}
