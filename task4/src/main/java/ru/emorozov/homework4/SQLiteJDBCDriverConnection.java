package ru.emorozov.homework4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteJDBCDriverConnection {

	private Connection connection;

	public SQLiteJDBCDriverConnection() {
		this.connect();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void connect() {
		try {
			String url = "jdbc:sqlite::resource:cinema.db";

			this.setConnection(DriverManager.getConnection(url));

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void disconnect() {
		try {
			this.getConnection().close();
			System.out.println("Connection to SQLite has been closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}