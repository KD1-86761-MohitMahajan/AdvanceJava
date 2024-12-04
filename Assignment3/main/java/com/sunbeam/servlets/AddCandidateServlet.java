package com.sunbeam.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.entities.Candidate;
import com.sunbeam.entities.User;

@WebServlet("/addCandidate")
public class AddCandidateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String party = req.getParameter("party");
		int vote=25;
		
		try(CandidateDao candDao = new CandidateDaoImpl())
		{
			Candidate cand = new Candidate(0, name, party, vote);
			int count = candDao.save(cand);
			
			if (count ==1 ) 
				System.out.println("Candidate added Successfully");
				
			resp.sendRedirect("http://localhost:8080/Voting/result");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	
}
	
	
	
}
