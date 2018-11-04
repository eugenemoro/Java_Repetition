package ru.emorozov.homework7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.emorozov.homework7.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
