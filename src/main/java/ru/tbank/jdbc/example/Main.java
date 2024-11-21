package ru.tbank.jdbc.example;

import ru.tbank.jdbc.example.entity.Student;
import ru.tbank.jdbc.example.mapper.StudentRowMapper;
import ru.tbank.jdbc.example.repository.StudentRepository;
import ru.tbank.jdbc.example.repository.impl.StudentRepositoryImpl;

import java.sql.SQLException;
import java.util.Optional;


public class Main {


    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        StudentRepository studentRepository = new StudentRepositoryImpl(dataSource, new StudentRowMapper());

        Optional<Student> optionalStudent = studentRepository.getById(10L);

        System.out.println(optionalStudent);
    }

}
