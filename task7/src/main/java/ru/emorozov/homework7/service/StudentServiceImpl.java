package ru.emorozov.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.emorozov.homework7.entity.Student;
import ru.emorozov.homework7.repository.StudentRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentRepository studentRepository;

	public Student create(Student student) {
		return studentRepository.save(student);
	}

	public Student delete(int id) {
		Student deletedStudent = studentRepository.findById(id).get();
		studentRepository.deleteById(id);
		return deletedStudent;
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student update(Student student) {
		Student updatedStudent = studentRepository.getOne(student.getId());
		updatedStudent.setName(student.getName());
		updatedStudent.setAge(student.getAge());
		return updatedStudent;
	}

	public Student findById(int id) {
		return studentRepository.findById(id).get();
	}
}
