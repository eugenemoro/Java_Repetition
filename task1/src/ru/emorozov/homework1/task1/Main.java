package ru.emorozov.homework1.task1;

public class Main {

	public static void main(String[] args) {
		PersonBuilder personBuilder = new PersonBuilder();
		try {
			Person person = personBuilder.addFirstName("Vasya").addGender("male").build();
			System.out.println(person.getFirstName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
