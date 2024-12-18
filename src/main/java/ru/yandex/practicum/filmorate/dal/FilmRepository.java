package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static ru.yandex.practicum.filmorate.dal.requests.FilmRequests.*;

@Repository
public class FilmRepository extends BaseRepository<Film> {
    public FilmRepository(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper);
    }

    public List<Film> getAllFilms() {
        return findMany(FIND_ALL_QUERY.query);
    }

    public Optional<Film> getFilmById(long filmId) {
        return findOne(FIND_BY_ID_QUERY.query, filmId);
    }

    public Film addNewFilm(Film film) {
        Long rating = null;
        if (film.getMpa() != null) {
            rating = film.getMpa().getId();
        }
        long id = insert(
                INSERT_QUERY.query,
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getRate(),
                rating
        );
        film.setId(id);
        return film;
    }

    public Film updateFilm(Film film) {
        Long rating = null;
        if (film.getMpa() != null) {
            rating = film.getMpa().getId();
        }
        update(
                UPDATE_QUERY.query,
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getRate(),
                rating,
                film.getId()
        );
        return film;
    }

    public boolean delete(long filmId) {
        return delete(DELETE_BY_ID_QUERY.query, filmId);
    }

    public List<Film> getPopularFilms(long count) {
        return findMany(POPULAR_FILM_QUERY.query, count);
    }
}
