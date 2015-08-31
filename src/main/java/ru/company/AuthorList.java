package ru.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorList {

	private List<Author> authorList = new ArrayList<>();

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "");
		return connection;
	}

	private List<Author> getAuthors() {

		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM author ORDER BY fio");

			while (result.next()) {
				Author author = new Author();
				author.setName(result.getString("fio"));
				authorList.add(author);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return authorList;
	}

	public List<Author> getAuthorList() {
		if (authorList.isEmpty()) {
			return getAuthors();
		}
		return authorList;
	}

}
