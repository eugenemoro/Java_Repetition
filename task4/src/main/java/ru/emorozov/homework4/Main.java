package ru.emorozov.homework4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Main {

	final static long MIN_30_MILLIS = 1800000l;
	final static long MILLIS_IN_MIN = 60000l;

	public static void main(String[] args) {
		try {
			SQLiteJDBCDriverConnection driverConnection = new SQLiteJDBCDriverConnection();
			movieOverlap(getScheduleInfo(driverConnection));
			bigGapBetweenMovies(getScheduleInfo(driverConnection));
			incomeInfoOnMovie(driverConnection);
			incomeByTime(driverConnection);
			driverConnection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static ResultSet getScheduleInfo(SQLiteJDBCDriverConnection driverConnection) throws SQLException {
		Statement st = driverConnection.getConnection().createStatement();

		return st.executeQuery("SELECT A.date, DATETIME(A.date, '+'||B.duration||' minutes') AS endtime, B.name, B.duration\n" +
						"FROM\n" +
						"  schedule A\n" +
						"  INNER JOIN movies B\n" +
						"    ON A.movie_id = B.id order by A.date");
	}

	private static void movieOverlap(ResultSet rs) throws SQLException {
		System.out.println("Movie overlap:");
		Calendar memDate = dateFromString("1900-01-01 00:00");
		Calendar memEndTime = dateFromString("1900-01-01 00:00");
		String memName = "";
		int memDuration = 0;
		while (rs.next()) {
			Calendar nextDate = dateFromString(rs.getString("date"));
			Calendar endTime = dateFromString(rs.getString("endtime"));
			String nextName = rs.getString("name");
			int duration = rs.getInt("duration");
			if (nextDate.before(memEndTime)) {
				System.out.println(
								memName + " " +
												calToString(memDate) + " " +
												memDuration + " " +
												nextName + " " +
												calToString(nextDate) + " " +
												duration);
			}
			memDate = nextDate;
			memEndTime = endTime;
			memName = nextName;
			memDuration = duration;
		}
	}

	private static void bigGapBetweenMovies(ResultSet rs) throws SQLException {
		System.out.println("Gap over 30 minutes:");
		rs.next();
		Calendar memDate = dateFromString(rs.getString("date"));
		Calendar memEndTime = dateFromString(rs.getString("endtime"));
		String memName = rs.getString("name");
		int memDuration = rs.getInt("duration");
		while (rs.next()) {
			Calendar nextDate = dateFromString(rs.getString("date"));
			Calendar endTime = dateFromString(rs.getString("endtime"));
			String nextName = rs.getString("name");
			int duration = rs.getInt("duration");
			long breakTime = nextDate.getTimeInMillis() - memEndTime.getTimeInMillis();
			if (breakTime > MIN_30_MILLIS) {
				System.out.println(
								memName + " " +
												calToString(memDate) + " " +
												memDuration + " " +
												nextName + " " +
												calToString(nextDate) + " " +
												breakTime / MILLIS_IN_MIN);
			}
			memDate = nextDate;
			memEndTime = endTime;
			memName = nextName;
			memDuration = duration;
		}
	}

	private static void incomeInfoOnMovie(SQLiteJDBCDriverConnection driverConnection) throws SQLException {
		System.out.println("Income info per movie:");
		ResultSet rs = driverConnection.getConnection().createStatement().executeQuery(
						"SELECT A.name, SUM(C.sold) AS \"total_sold\", SUM(C.sold * C.price) AS \"total_income\"\n" +
										"FROM\n" +
										"  movies A\n" +
										"INNER JOIN schedule B\n" +
										"    ON A.id = B.movie_id\n" +
										"INNER JOIN tickets C\n" +
										"    ON B.id = C.showtime\n" +
										"GROUP BY A.id"
		);
		while (rs.next()) {
			System.out.println(
							rs.getString("name") + " " +
											rs.getInt("total_sold") + " " +
											rs.getInt("total_income")
			);
		}
		rs = driverConnection.getConnection().createStatement().executeQuery(
						"SELECT SUM(C.sold) AS \"total_sold\", SUM(C.sold * C.price) AS \"total_income\"\n" +
										"FROM\n" +
										"  movies A\n" +
										"INNER JOIN schedule B\n" +
										"    ON A.id = B.movie_id\n" +
										"INNER JOIN tickets C\n" +
										"    ON B.id = C.showtime"
		);
		System.out.println("Total: " + rs.getInt("total_sold") + " tickets for " + rs.getInt("total_income"));
	}

	private static void incomeByTime(SQLiteJDBCDriverConnection driverConnection) throws SQLException {
		System.out.println("Income by time:");
		PreparedStatement preparedStatement = driverConnection.getConnection().prepareStatement(
						"SELECT SUM(B.sold) AS \"total_sold\", SUM(B.sold * B.price) AS \"total_income\"\n" +
										"FROM\n" +
										"  schedule A\n" +
										"INNER JOIN tickets B\n" +
										"  ON A.id = B.showtime\n" +
										"WHERE \n" +
										"  A.date >= DATETIME(A.date,'start of day','+'||?||' hours') \n" +
										"  AND \n" +
										"  A.date < DATETIME(A.date,'start of day','+'||?||' hours')\n"
		);
		ArrayList<Integer> listOfHours = new ArrayList<>();
		int fromHour = 6;
		int toHour;
		while ((fromHour += 3) <= 24) {
			listOfHours.add(fromHour);
		}
		for (int i = 0; i < listOfHours.size() - 1; i++) {
			fromHour = listOfHours.get(i);
			toHour = listOfHours.get(i + 1);
			System.out.print("between " + fromHour + " and " + toHour + " ");
			preparedStatement.setInt(1, fromHour);
			preparedStatement.setInt(2, toHour);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(
							resultSet.getInt("total_sold") + " " +
											resultSet.getInt("total_income")
			);
		}
	}

	private static Calendar dateFromString(String s) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(s));
			return calendar;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String calToString(Calendar calendar) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(calendar.getTime());
	}
}
