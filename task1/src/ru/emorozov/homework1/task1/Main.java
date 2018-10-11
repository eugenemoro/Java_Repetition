package ru.emorozov.homework1.task1;

public class Main {

	public static void main(String[] args) {
		PersonBuilder personBuilder = new PersonBuilder();
		personBuilder.addFirstName("Vasya");
		personBuilder.addGender("male");
		try {
			Person person = personBuilder.build();
			System.out.println(person.getFirstName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
