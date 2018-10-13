package ru.emorozov.homework1.task2;

//interfaces should be implemented not extended
class Lorry extends Car {

	@Override
	public void move() {
		System.out.println("Car is moving");
	}

	//method wasn't overridden
	@Override
	void open() {
		System.out.println("Car is opened");
	}

	@Override
	public void stop() {
		System.out.println("Car is stopped");
	}
}