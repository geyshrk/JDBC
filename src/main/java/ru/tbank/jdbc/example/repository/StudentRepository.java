package ru.tbank.jdbc.example.repository;

import ru.tbank.jdbc.example.entity.Student;

import java.util.List;


public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> getAllByName(String name);

}
