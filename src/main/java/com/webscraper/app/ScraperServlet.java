package com.webscraper.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.webscraper.entity.Stock;

@WebServlet("/TestDbServlet")
public class ScraperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get connection to db
		try {
			PrintWriter out = response.getWriter();

			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Stock.class)
					.buildSessionFactory();

			Session session = factory.getCurrentSession();

			out.println("SUCCESS!!");

			session.close();
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new ServletException(exe);
		}
	}
}
