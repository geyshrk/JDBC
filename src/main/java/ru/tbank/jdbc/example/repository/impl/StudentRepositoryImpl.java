package ru.tbank.jdbc.example.repository.impl;

import lombok.RequiredArgsConstructor;
import ru.tbank.jdbc.example.DataSource;
import ru.tbank.jdbc.example.JdbcTemplate;
import ru.tbank.jdbc.example.entity.Student;
import ru.tbank.jdbc.example.mapper.RowMapper;
import ru.tbank.jdbc.example.mapper.StudentRowMapper;
import ru.tbank.jdbc.example.repository.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



// в атрибутах jdbctemplate
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final DataSource dataSource;
    private final RowMapper<Student> rowMapper;
    private final static JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private final static String GET_ALL = "select s.id, s.first_name, s.last_name, s.age, s.teacher_id, t.degree," +
            "t.first_name AS teacher_first_name, t.last_name AS teacher_last_name " +
            "from students AS s INNER JOIN teachers AS t ON t.id = s.teacher_id";
    private final static String GET_BY_ID = GET_ALL + " where id = ?";
    private final static String GET_ALL_BY_NAME = GET_ALL + " where first_name = ?";
    private final static String DELETE = "";
    private final static String UPDATE = "";

    @Override
    public Optional<Student> getById(Long id) {
        List<Student> result = jdbcTemplate.execute(GET_BY_ID, new StudentRowMapper(), id);
        if (result.isEmpty()) return Optional.empty();
        return Optional.of(result.getFirst());
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.execute(GET_ALL, new StudentRowMapper());
    }

    @Override
    public boolean update(Student type) {
        return false;
    }

    @Override
    public boolean delete(Student type) {
        return false;
    }

    @Override
    public List<Student> getAllByName(String name) {
        return jdbcTemplate.execute(GET_ALL_BY_NAME, new StudentRowMapper(), name);
    }

}
