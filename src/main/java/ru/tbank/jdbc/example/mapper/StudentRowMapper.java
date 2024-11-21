package ru.tbank.jdbc.example.mapper;

import ru.tbank.jdbc.example.entity.Student;
import ru.tbank.jdbc.example.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRowMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet resultSet) throws SQLException {
        return Student.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .age(resultSet.getInt("age"))
                .teacher(Teacher.builder()
                        .id(resultSet.getLong("teacher_id"))
                        .firstName("teacher_first_name")
                        .lastName("teacher_last_name")
                        .degree("degree")
                        .build())
                .build();
    }

}
