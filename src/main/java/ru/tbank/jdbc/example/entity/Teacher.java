package ru.tbank.jdbc.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {

    private Long id;

    private String firstName;

    private String lastName;

    private String degree;

    private List<Student> students;

}
