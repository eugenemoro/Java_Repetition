package ru.emorozov.homework1.task1;

public class PersonBuilder{
	private String firstName;
	private String lastName;
	private String middleName;
	private String country;
	private String address;
	private String phone;
	private int age;
	private String gender;

	public void addFirstName(String firstName){
		this.firstName = firstName;
	}
	public void addLastName(String lastName){
		this.lastName = lastName;
	}
	public void addMiddleName(String middleName){
		this.middleName = middleName;
	}
	public void addCountry(String country){
		this.country = country;
	}
	public void addAddress(String address){
		this.address = address;
	}
	public void addPhone(String phone){
		this.phone = phone;
	}
	public void addAge(int age){
		this.age = age;
	}
	public void addGender(String gender){
		this.gender = gender;
	}

	public Person build() throws Exception{
		if (firstName == null) throw new Exception("The one cannot have no name!");
		if (gender == null) throw new Exception("The one cannot have no gender!");
		Person person = new Person();
		person.setAddress(address);
		person.setAge(age);
		person.setCountry(country);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setMiddleName(middleName);
		person.setGender(gender);
		person.setPhone(phone);
		return person;
	}
}
