package ru.company.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		// NOP
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest wrappedRequest = (HttpServletRequest) request;
		HttpServletResponse wrappedResponse = (HttpServletResponse) response;

		HttpSession session = wrappedRequest.getSession(false);
		if (session == null || session.isNew()) {
			wrappedResponse.sendRedirect(wrappedRequest.getContextPath() + "/index.jsp");
		} else {
			chain.doFilter(wrappedRequest, wrappedResponse);
		}
	}

	public void destroy() {
		// NOP
	}

}
