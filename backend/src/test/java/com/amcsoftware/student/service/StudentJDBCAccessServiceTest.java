package com.amcsoftware.student.service;

import com.amcsoftware.AbstractTestContainer;
import com.amcsoftware.rowMappers.StudentRowMapper;
import com.amcsoftware.student.model.Gender;
import com.amcsoftware.student.model.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StudentJDBCAccessServiceTest extends AbstractTestContainer {
    private StudentJDBCAccessService underTest;
    private final StudentRowMapper  studentRowMapper = new StudentRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new StudentJDBCAccessService(getJdbcTemplate(),studentRowMapper);
    }

    @Test
    void selectAllStudents() {
        Student student = new Student(UUID.randomUUID(), "Vanessa", "data", "aieji@gmail.com", "12345"
                , "770-741-1000", 35,Gender.FEMALE );
        underTest.insertStudent(student);

        List<Student> students = underTest.selectAllStudents();

        assertThat(students).isNotEmpty();

    }

    @Test
    void findStudentById() {
        UUID id = UUID.fromString("fc15c009-43f6-49ce-998e-f57127c26239");
        String email = "aiei@gmail.com";
        Student student = new Student(id, "Vanessa", "data", email, "1234"
                , "770-741-1000", 35, Gender.FEMALE );
        underTest.insertStudent(student);

       UUID testId =  underTest.selectAllStudents().stream()
                .filter(s -> s.getEmail().equals(email)).map(Student::getId).findFirst().orElseThrow();

        Optional<Student> actual = underTest.findStudentById(testId);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(testId);
            assertThat(c.getFirstName()).isEqualTo(student.getFirstName());
            assertThat(c.getLastName()).isEqualTo(student.getLastName());
            assertThat(c.getEmail()).isEqualTo(student.getEmail());
            assertThat(c.getPhoneNumber()).isEqualTo(student.getPhoneNumber());
            assertThat(c.getAge()).isEqualTo(student.getAge());
            assertThat(c.getGender()).isEqualTo(student.getGender());
        });
    }

 @Test
    void returnEmptyWhenSelectingStudentById() {
         UUID id = UUID.fromString("fc15c009-43f6-49ce-998e-f57127c26232");

        Optional<Student> actual = underTest.findStudentById(id);

        assertThat(actual).isEmpty();
    }

    @Test
    void insertStudent() {
        UUID id = UUID.fromString("fc15c009-43f6-49ce-998e-f57127c26230");
        String email = "grap@gmail.com";
        Student student = new Student(id, "Dra", "Trac", email, "1256"
                , "770-741-112", 35, Gender.FEMALE);
        underTest.insertStudent(student);
        UUID testId =  underTest.selectAllStudents().stream()
                .filter(s -> s.getEmail().equals(email)).map(Student::getId).findFirst().orElseThrow();

        Optional<Student> actual = underTest.findStudentById(testId);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(testId);
            assertThat(c.getFirstName()).isEqualTo(student.getFirstName());
            assertThat(c.getLastName()).isEqualTo(student.getLastName());
            assertThat(c.getEmail()).isEqualTo(student.getEmail());
            assertThat(c.getPhoneNumber()).isEqualTo(student.getPhoneNumber());
            assertThat(c.getAge()).isEqualTo(student.getAge());
            assertThat(c.getGender()).isEqualTo(student.getGender());
        });

    }

    @Test
    void existWithEmail() {
       String email = "newei@gmail.com";
       boolean isEmail = underTest.isEmail(email);
       assertThat(isEmail).isFalse();
        UUID id = UUID.fromString("fc15c007-43f6-49ce-998e-f57127c26236");
        Student student = new Student(id, "Qway", "Jackson", email, "246"
                , "770-741-1120", 38,Gender.FEMALE );
        underTest.insertStudent(student);

        isEmail = underTest.isEmail(email);

        assertThat(isEmail).isTrue();

    }

    @Test
    void existById() {
        UUID id = UUID.fromString("fc15c009-43f6-49ce-998e-f57127c26212");
        String email = "gr@gmail.com";
        Student student = new Student(id, "ra", "rac", email, "246"
                , "770-749-1125", 39,Gender.FEMALE);
        underTest.insertStudent(student);

        UUID testId =  underTest.selectAllStudents().stream()
                .filter(s -> s.getEmail().equals(email)).map(Student::getId).findFirst().orElseThrow();

        boolean doesIdExist = underTest.existById(testId);

        assertThat(doesIdExist).isTrue();
    }

    @Test
    void deleteStudentWithId() {
        UUID id = UUID.fromString("fc15c009-43f6-49ce-998e-f57127c26230");
        String email = "g@gmail.com";
        Student student = new Student(id, "Dra", "Trac", email, "246"
                , "770-741-112", 35, Gender.FEMALE);
        underTest.insertStudent(student);

        UUID uid = underTest.selectAllStudents().stream().filter(person -> person.getEmail().equals(email))
                .map(Student::getId).findFirst().orElseThrow();

        boolean exist = underTest.existById(uid);

        assertThat(exist).isTrue();

        underTest.deleteStudentWithId(uid);

        exist = underTest.existById(uid);

        assertThat(exist).isFalse();
    }

}