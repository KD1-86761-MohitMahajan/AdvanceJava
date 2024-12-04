package com.sunbeam.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.entities.User;

@WebServlet("/AddUser")
public class RegistrationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String birthdate = req.getParameter("dob");
		Date dob = Date.valueOf(birthdate);
		String role = req.getParameter("role");
		
		try(UserDao userDao = new UserDaoImpl())
		{
			User newUser = new User(0, first_name, last_name, email, password, dob, 0, role);
			int count = userDao.save(newUser);
			
			if (count ==1 ) 
				System.out.println("User Registered Successfully");
				
			resp.sendRedirect("index.html");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	
}
}
