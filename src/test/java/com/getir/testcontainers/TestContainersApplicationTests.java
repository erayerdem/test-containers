package com.getir.testcontainers;

import com.getir.testcontainers.dao.StudentRepository;
import com.getir.testcontainers.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@Testcontainers
class TestContainersApplicationTests {


    @Autowired
    private StudentRepository repository;
    @Container
    private static PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest");

    @DynamicPropertySource
    public static void dynamicPropert(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    void getSize() {
        List<Student> all = repository.findAll();
        assertThat(all).hasSize(1);

    }

}
