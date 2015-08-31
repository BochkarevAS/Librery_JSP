package ru.company.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		HashMap sessionMap = (HashMap) context.getAttribute("sessionMap");
		sessionMap.put(session.getId(), session);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		HashMap sessionMap = (HashMap) context.getAttribute("sessionMap");
		sessionMap.remove(session.getId());
	}

}
