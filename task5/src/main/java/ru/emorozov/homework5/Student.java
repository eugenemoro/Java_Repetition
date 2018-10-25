package ru.emorozov.homework5;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "mark")
	private int mark;

	public Student() {
	}

	public Student(String name, int mark) {
		this.name = name;
		this.mark = mark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return this.getId()+ " " +this.getName() + " " + this.getMark();
	}
}
