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

	public PersonBuilder addFirstName(String firstName){
		this.firstName = firstName;
		return this;
	}
	public PersonBuilder addLastName(String lastName){
		this.lastName = lastName;
		return this;
	}
	public PersonBuilder addMiddleName(String middleName){
		this.middleName = middleName;
		return this;
	}
	public PersonBuilder addCountry(String country){
		this.country = country;
		return this;
	}
	public PersonBuilder addAddress(String address){
		this.address = address;
		return this;
	}
	public PersonBuilder addPhone(String phone){
		this.phone = phone;
		return this;
	}
	public PersonBuilder addAge(int age){
		this.age = age;
		return this;
	}
	public PersonBuilder addGender(String gender){
		this.gender = gender;
		return this;
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
