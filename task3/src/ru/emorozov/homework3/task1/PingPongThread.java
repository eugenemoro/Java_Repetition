package ru.emorozov.homework3.task1;

public class PingPongThread extends Thread {
	private String msg;
	private static String turn = "";

	public PingPongThread(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		while (true) {
			playTurn();
		}
	}

	public void playTurn() {
		synchronized (PingPongThread.class) {
			if (!msg.equals(turn)) {
				turn = msg;
				System.out.println(msg);
			}
		}
	}
}
