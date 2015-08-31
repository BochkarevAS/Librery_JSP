package ru.company.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.company.Book;

public class ImageServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("image/jpeg");
		try (OutputStream out = response.getOutputStream()) {
			int index = Integer.valueOf(request.getParameter("index"));

			List<Book> list = (ArrayList<Book>) request.getSession(false).getAttribute("currentBookList");
			Book book = list.get(index);
			response.setContentLength(book.getImage().length);
			out.write(book.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
