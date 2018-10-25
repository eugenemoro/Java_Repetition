package ru.emorozov.homework5;

import java.util.List;

public class StudentService {
	private static StudentDAO studentDAO;

	public StudentService() {
		studentDAO = new StudentDAO();
	}

	public void persist(Student entity) {
		studentDAO.openCurrentSessionwithTransaction();
		studentDAO.persist(entity);
		studentDAO.closeCurrentSessionwithTransaction();
	}

	public void update(Student entity) {
		studentDAO.openCurrentSessionwithTransaction();
		studentDAO.update(entity);
		studentDAO.closeCurrentSessionwithTransaction();
	}

	public Student findById(String id) {
		studentDAO.openCurrentSession();
		studentDAO.findById(id);
		studentDAO.closeCurrentSession();
		return null;
	}

	public void delete(String id) {
		studentDAO.openCurrentSessionwithTransaction();
		Student entity = findById(id);
		studentDAO.delete(entity);
		studentDAO.closeCurrentSessionwithTransaction();
	}

	public List<Student> findAll() {
		studentDAO.openCurrentSession();
		List<Student> studentList = studentDAO.findAll();
		studentDAO.closeCurrentSession();
		return studentList;
	}

	public void deleteAll() {
		studentDAO.openCurrentSessionwithTransaction();
		studentDAO.deleteAll();
		studentDAO.closeCurrentSessionwithTransaction();
	}

	public StudentDAO studentDAO() {
		return studentDAO;
	}
}
