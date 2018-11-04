package ru.emorozov.homework7.service;

import ru.emorozov.homework7.entity.Student;

import java.util.List;

public interface StudentService {

	public Student create(Student student);

	public Student delete(int id);

	public List<Student> findAll();

	public Student update(Student student);

	public Student findById(int id);
}
