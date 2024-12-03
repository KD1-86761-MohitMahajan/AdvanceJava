package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.entities.Candidate;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Candidate> list = new ArrayList<>();
		
		try(CandidateDao candDao = new CandidateDaoImpl()) {
			list = candDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body>");
		
		
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

		out.println("<h2 style='text-align:center; color:#333;'>Voting Result</h2>");
		out.println("<table border='1' style='width:100%; border-collapse:collapse; text-align:left;'>");
		out.println("<thead style='background-color:#f4f4f4;'>");
		out.println("<tr>");
		out.println("<th style='padding:10px;'>Id</th>");
		out.println("<th style='padding:10px;'>Name</th>");
		out.println("<th style='padding:10px;'>Party</th>");
		out.println("<th style='padding:10px;'>Votes</th>");
		out.println("<th style='padding:10px;'>Action</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		for (Candidate c : list) {
		    out.println("<tr>");
		    out.printf("<td style='padding:10px;'>%d</td>\n", c.getId());
		    out.printf("<td style='padding:10px;'>%s</td>\n", c.getName());
		    out.printf("<td style='padding:10px;'>%s</td>\n", c.getParty());
		    out.printf("<td style='padding:10px;'>%d</td>\n", c.getVotes());
		    out.printf("<td style='padding:10px;'>Edit/Delete</td>\n"); // Placeholder for actions
		    out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<p style='text-align:center; margin-top:20px;'>");
		out.println("<a href='logout' style='color:#2575fc; text-decoration:none; margin-right:15px;'>Sign Out</a>");
		out.println("<a href='newCandidate.html' style='color:#2575fc; text-decoration:none;'>Add Candidate</a>");
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");

	}
}

