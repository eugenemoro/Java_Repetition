package ru.emorozov.homework3.task1;

public class MainTask1 {
	public static void main(String[] args) {
		PingPongThread thread1 = new PingPongThread("PING");
		PingPongThread thread2 = new PingPongThread("pong");
		thread1.start();
		thread2.start();
	}
}
