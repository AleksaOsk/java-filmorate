package ru.yandex.practicum.filmorate.dto.mpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MpaResponseDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String name;
}
