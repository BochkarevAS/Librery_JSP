package ru.company.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

public class ContextListener implements ServletContextListener {

	private Map<String, HttpSession> sessionMap = new HashMap<>();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("sessionMap", sessionMap);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("sessionMap");
		sessionMap = null;
	}
}
