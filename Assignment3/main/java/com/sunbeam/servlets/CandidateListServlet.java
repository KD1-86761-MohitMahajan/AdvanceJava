package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.entities.Candidate;

@WebServlet ("/candlist")
public class CandidateListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		List <Candidate> list = new ArrayList<>();
		
		try (CandidateDao candDao = new CandidateDaoImpl()){
			list = candDao.findAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		// display list radio button
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Candidate List</title>");
		out.println("</head>");
		out.println("<body>");
		
		ServletContext app = this.getServletContext();
		String appTitle = app.getInitParameter("app.title");
		out.printf("<h1>%s</h1>", appTitle);
		
		
		// Cookie
		Cookie[] arr = req.getCookies();
		String userName = "", role = "";
		if(arr != null) {
			for (Cookie c : arr) {
				if(c.getName().equals("uname"))
					userName = c.getValue();
				if(c.getName().equals("role"))
					role = c.getValue();
			}
		}
			
out.printf("Hello, %s (%s)<hr/>\n", userName, role);
		
ServletContext ctx = this.getServletContext();
String ann = (String) ctx.getAttribute("announcement");
if(ann != null)
	out.println("<p style='color:red'> NOTE: " + ann + "</p>");



out.println("<html>");
out.println("<head>");
out.println("<title>Voting Page</title>");
out.println("</head>");
ServletContext ctx2 = this.getServletContext();
String bgcolor = ctx2.getInitParameter("background-color");
out.printf("<body style='font-family: Arial, sans-serif; background-color: %s; margin: 20px;'>",bgcolor);
out.println("<h2 style='color: #333;'>Candidate List</h2>");
out.println("<form method='post' action='vote'>");
for(Candidate c : list) {
    out.printf("<input type='radio' name='candidate' value='%d' style='margin-bottom: 10px;'/> <span style='font-size: 18px; color: #555;'>%s</span> <br/>\n", 
            c.getId(), c.getName());
}
out.println("<br/><input type='submit' value='Vote' style='padding: 10px 20px; background-color: #4CAF50; color: white; border: none; cursor: pointer; font-size: 16px; border-radius: 5px;'/>");
out.println("</form>");
out.println("</body>");
out.println("</html>");

	}
		
		
		
		
	}

	

