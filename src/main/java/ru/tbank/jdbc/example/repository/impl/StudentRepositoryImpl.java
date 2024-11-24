package ru.tbank.jdbc.example.repository.impl;

import lombok.RequiredArgsConstructor;
import ru.tbank.jdbc.example.DataSource;
import ru.tbank.jdbc.example.JdbcTemplate;
import ru.tbank.jdbc.example.entity.Student;
import ru.tbank.jdbc.example.mapper.RowMapper;
import ru.tbank.jdbc.example.mapper.StudentRowMapper;
import ru.tbank.jdbc.example.repository.Exceptions.InvalidResultsNumberException;
import ru.tbank.jdbc.example.repository.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final RowMapper<Student> rowMapper;
    private final JdbcTemplate jdbcTemplate;
    private final static String GET_ALL = "SELECT s.id, s.first_name, s.last_name, s.age, s.teacher_id, t.degree," +
            "t.first_name AS teacher_first_name, t.last_name AS teacher_last_name " +
            "FROM students AS s INNER JOIN teachers AS t ON t.id = s.teacher_id";
    private final static String GET_BY_ID = GET_ALL + " WHERE id = ?";
    private final static String GET_ALL_BY_NAME = GET_ALL + " WHERE first_name = ?";
    private final static String DELETE = "DELETE FROM students WHERE id = ?";
    private final static String UPDATE = "UPDATE students " +
            "SET first_name = ?, " +
            "last_name = ?, " +
            "teacher_id = ? " +
            "age = ? " +
            "WHERE id = ?";

    @Override
    public Optional<Student> getById(Long id) {
        List<Student> result = jdbcTemplate.execute(GET_BY_ID, rowMapper, id);
        return getOneStudent(result);
    }
    private Optional<Student> getOneStudent(List<Student> result){
        if (result.size() > 1) throw new InvalidResultsNumberException("Больше 1 результата");
        return result.stream().findAny();
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.execute(GET_ALL, rowMapper);
    }

    @Override
    public boolean update(Student type) {
        return jdbcTemplate.executeUpdate(UPDATE,
                type.getFirstName(),
                type.getLastName(),
                type.getTeacher().getId(),
                type.getAge(),
                type.getId());
    }

    @Override
    public boolean delete(Student type) {
        return jdbcTemplate.executeUpdate(DELETE, type.getId());
    }

    @Override
    public List<Student> getAllByName(String name) {
        return jdbcTemplate.execute(GET_ALL_BY_NAME, rowMapper, name);
    }

}
