package ru.tbank.jdbc.example.repository;

import ru.tbank.jdbc.example.entity.Student;

import java.util.List;
import java.util.Optional;


interface CrudRepository<T, ID> {

    Optional<T> getById(ID id);

    List<Student> getAll();

    boolean update(T type);

    boolean delete(T type);

}
