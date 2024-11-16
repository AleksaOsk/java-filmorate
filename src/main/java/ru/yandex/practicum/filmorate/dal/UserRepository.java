package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static ru.yandex.practicum.filmorate.dal.requests.UserRequests.*;

@Repository
public class UserRepository extends BaseRepository<User> {
    public UserRepository(JdbcTemplate jdbc, RowMapper<User> mapper) {
        super(jdbc, mapper);
    }

    public List<User> getAllUsers() {
        return findMany(FIND_ALL_QUERY.query);
    }

    public Optional<User> getUserById(long userId) {
        return findOne(FIND_BY_ID_QUERY.query, userId);
    }

    public User addNewUser(User user) {
        long id = insert(
                INSERT_QUERY.query,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                Date.valueOf(user.getBirthday())
        );
        user.setId(id);
        return user;
    }

    public User updateUser(User user) {
        update(
                UPDATE_QUERY.query,
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                Date.valueOf(user.getBirthday()),
                user.getId()
        );
        return user;
    }

    public boolean delete(long userId) {
        return delete(DELETE_BY_ID_QUERY.query, userId);
    }
}