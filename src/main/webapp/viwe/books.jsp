<%@ include file="../WEB-INF/jspf/aside.jspf" %>
<%@ include file="../WEB-INF/jspf/letters.jspf" %>

<jsp:useBean id="bookList" class="ru.company.BookList" scope="page" />

	 <%
	 	List<Book> list = null;
	 
	 	if (request.getParameter("genre_id") != null) {
	 		 long id = Long.valueOf(request.getParameter("genre_id"));
	         list = bookList.getBooksByGenre(id);
	         
	 	} else if (request.getParameter("letter") != null) {
	 		String letter = request.getParameter("letter");
            list = bookList.getBooksByLetter(letter);
            
	 	} else if (request.getParameter("search_content") != null) {
	 		String searchStr = request.getParameter("search_content");
            SearchType type = SearchType.TITLE;
            
            if (request.getParameter("search_option").equals("Автор")) {
                type = SearchType.AUTHOR;
            }

            if (searchStr != null && !searchStr.trim().equals("")) {
                list = bookList.getBooksBySearch(searchStr, type);
            }
	 	}
	 
	 	request.getSession();
	 	session.setAttribute("currentBookList", list);
	 	
	 	if (list != null) {
      %>

<section>
	<h3>${param.genre_name}</h3>
	<%-- <c:forEach var="book" items="${bookList.getBooksByGenre(param.genre_id)}"> --%>
	<% for (Book book : list) { %>
		<table>
			<tr><th><%= book.getName() %></th></tr>
			<tr><td><img src="<%= request.getContextPath() %>/ImageServlet?index=<%=list.indexOf(book)%>" height="250" width="190" alt="Обложка"/></td></tr>
			<tr><td><b>ISBN: </b><%= book.getIsbn() %></td></tr>
			<tr><td><b>Издательство: </b><%= book.getPublisher() %></td></tr>
			<tr><td><b>Количество страниц: </b><%= book.getPageCount() %></td></tr>
			<tr><td><b>Год издания: </b><%= book.getPublishDate() %></td></tr>
			<tr><td><b>Автор: </b><%= book.getAuthor() %></td></tr>
			<tr><td><a href="content.jsp?index=<%=list.indexOf(book)%>&session_id=<%=request.getSession().getId()%>">Читать</a></td></tr>
		</table>
	<%-- </c:forEach> --%>
	<% } }%>
</section>
