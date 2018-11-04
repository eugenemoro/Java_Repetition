package ru.emorozov.homework7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.emorozov.homework7.entity.Student;
import ru.emorozov.homework7.service.StudentService;

import java.util.List;

@Controller
@RequestMapping(value="/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addStudentPage() {
		ModelAndView modelAndView = new ModelAndView("add-student-form");
		modelAndView.addObject("student", new Student());
		return modelAndView;
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingStudent(@ModelAttribute Student student) {

		ModelAndView modelAndView = new ModelAndView("home");
		studentService.create(student);

		String message = "Student was successfully added.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value="/list")
	public ModelAndView listOfStudents() {
		ModelAndView modelAndView = new ModelAndView("list-of-students");

		List<Student> students = studentService.findAll();
		modelAndView.addObject("students", students);

		return modelAndView;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-student-form");
		Student student = studentService.findById(id);
		modelAndView.addObject("student",student);
		return modelAndView;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingStudent(@ModelAttribute Student student, @PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("home");

		studentService.update(student);

		String message = "Student was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		studentService.delete(id);
		String message = "Student was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
