package ru.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookList {

	private List<Book> bookList = new ArrayList<>();

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "");
		return connection;
	}

	private List<Book> getBooks(String str) {

		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(str);

			while (result.next()) {
				Book book = new Book();
				book.setId(result.getLong("id"));
				book.setName(result.getString("name"));
				book.setGenre(result.getString("genre"));
				book.setIsbn(result.getString("isbn"));
				book.setAuthor(result.getString("author"));
				book.setPageCount(result.getInt("page_count"));
				book.setPublishDate(result.getInt("publish_year"));
				book.setPublisher(result.getString("publisher"));
				book.setImage(result.getBytes("image"));
				bookList.add(book);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public List<Book> getBookList() {
		if (bookList.isEmpty()) {
			return getBooks("SELECT * FROM book ORDER BY name");
		}
		return bookList;
	}

	public List<Book> getAllBooks() {
		return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, "
				+ "a.fio as author, g.name as genre, b.image from book b inner join author a on b.author_id=a.id "
				+ "inner join genre g on b.genre_id=g.id inner join publisher p on b.publisher_id=p.id order by b.name");
	}

	public List<Book> getBooksByGenre(long id) {
		if (id == 0) {
			return getAllBooks();
		} else {
			return getBooks(
					"select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
							+ "inner join author a on b.author_id=a.id " + "inner join genre g on b.genre_id=g.id "
							+ "inner join publisher p on b.publisher_id=p.id " + "where genre_id=" + id
							+ " order by b.name " + "limit 0,5");
		}
	}

	public List<Book> getBooksByLetter(String letter) {

		return getBooks(
				"select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
						+ "inner join author a on b.author_id=a.id " + "inner join genre g on b.genre_id=g.id "
						+ "inner join publisher p on b.publisher_id=p.id " + "where substr(b.name,1,1)='" + letter
						+ "' order by b.name " + "limit 0,5");

	}

	public List<Book> getBooksBySearch(String searchStr, SearchType type) {
		StringBuilder sql = new StringBuilder(
				"select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
						+ "inner join author a on b.author_id=a.id " + "inner join genre g on b.genre_id=g.id "
						+ "inner join publisher p on b.publisher_id=p.id ");

		if (type == SearchType.AUTHOR) {
			sql.append("where lower(a.fio) like '%" + searchStr.toLowerCase() + "%' order by b.name ");

		} else if (type == SearchType.TITLE) {
			sql.append("where lower(b.name) like '%" + searchStr.toLowerCase() + "%' order by b.name ");
		}
		sql.append("limit 0,5");

		return getBooks(sql.toString());

	}
}
