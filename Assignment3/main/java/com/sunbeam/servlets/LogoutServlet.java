package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie c1 = new Cookie("uname", "");
		c1.setMaxAge(-1);
		resp.addCookie(c1);
		Cookie c2 = new Cookie("role", "");
		c2.setMaxAge(-1);
		resp.addCookie(c2);
		HttpSession session = req.getSession();
		session.invalidate();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		ServletContext ctx2 = this.getServletContext();
		String bgcolor = ctx2.getInitParameter("background-color");
		out.printf("<body style='font-family: Arial, sans-serif; background-color: %s; margin: 20px;'>",bgcolor);
		out.println("<title>Logout</title>");
		out.println("</head>");
		out.println("<body>");
		
		ServletContext app = this.getServletContext();
		String appTitle = app.getInitParameter("app.title");
		out.printf("<h1>%s</h1>", appTitle);
		
		
		out.println("<h2>Thank you</h2>");
		out.println("<p>u voted to save democracy, LOL.</p>");
		out.println("<p><a href='index.html'>Login Again</a></p>");
		out.println("</body>");
		out.println("</html>");	
	}
}
