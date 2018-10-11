package ru.emorozov.homework1.task3;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Shape> shapes = new ArrayList<>();
		shapes.add(new Circle());
		shapes.add(new Circle());
		shapes.add(new Triangle());
		shapes.add(new Square());
		shapes.add(new Circle());
		shapes.stream().forEach(shape -> shape.draw());
	}
}
