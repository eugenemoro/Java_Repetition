package ru.emorozov.homework5;

public class Main {
	public static void main(String[] args) {
		StudentService studentService = new StudentService();
//		for (int i = 0; i < 1000; i++) {
//			studentService.persist(new Student("student" + i, (int) Math.round(Math.random() * 5)));
//		}
//		for (Student student : studentService.findAll()) {
//			System.out.println(student);
//		}
//		studentService.deleteAll();
		System.out.println(studentService.findById("4028b88166ace0c20166ace0f4bc008e"));
//		studentService.delete("4028b88166ace0c20166ace0c5970000");
	}
}
