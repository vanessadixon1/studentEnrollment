package com.amcsoftware.student.service;


import com.amcsoftware.AbstractTestContainer;
import com.amcsoftware.student.model.Student;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest extends AbstractTestContainer {
    @Autowired
    private StudentRepository underTest;

    @Test
    void existsStudentByEmail() {
        String email = "aiei@gmail.com";

        boolean exist = underTest.existsStudentByEmail(email);

        assertThat(exist).isFalse();

    }



}