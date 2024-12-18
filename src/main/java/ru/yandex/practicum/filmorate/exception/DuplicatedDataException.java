package ru.yandex.practicum.filmorate.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicatedDataException extends RuntimeException {
    private String reason;

    public DuplicatedDataException(String reason) {
        this.reason = reason;
    }
}