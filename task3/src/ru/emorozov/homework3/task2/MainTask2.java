package ru.emorozov.homework3.task2;

import java.util.concurrent.locks.ReentrantLock;

public class MainTask2 {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Counter counter = new Counter();

		for (int i = 0; i < 100; i++) {
			new Thread(new CounterRunnable(counter, lock)).start();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter.getCounter());
	}
}
