package ru.emorozov.homework3.task2;

import java.util.concurrent.locks.Lock;

public class Counter {
	private int counter;

	public Counter() {
		this.counter = 0;
	}

	public void inc() {
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}
