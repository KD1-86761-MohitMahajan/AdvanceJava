package com.sunbeam.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/announce")
public class AnnouncementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the announcement from the form
        String announcement = request.getParameter("ann");

        // Store it in the servlet context to share it across different servlets or pages
        ServletContext ctx = this.getServletContext();
        ctx.setAttribute("announcement", announcement);

        // Redirect to the result page to display the announcement
        response.sendRedirect("result");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For this example, we'll just show a message if someone accesses the announce page via GET
        response.setContentType("text/html");
        response.getWriter().println("<h2>Submit an Announcement</h2>");
        response.getWriter().println("<p>Please submit an announcement using the form below.</p>");
    }
}
