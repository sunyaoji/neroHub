package com.aowin.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Listener implements HttpSessionListener,ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext app=sce.getServletContext();
		app.setAttribute("visit", 0);
	}
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext app=se.getSession().getServletContext();
		int visit=Integer.parseInt(String.valueOf(app.getAttribute("visit")));
		visit++;
		app.setAttribute("visit",visit);
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


