package ru.emorozov.homework3.task2;

import java.util.concurrent.locks.ReentrantLock;

public class CounterRunnable implements Runnable {
	private Counter counter;
	private ReentrantLock lock;

	public CounterRunnable(Counter counter, ReentrantLock lock) {
		this.counter = counter;
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.lock();
		for (int j = 0; j < 100; j++) {
			counter.inc();
		}
		lock.unlock();
	}
}
