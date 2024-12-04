package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
        List<Candidate> list;

        try (CandidateDao candDao = new CandidateDaoImpl()) {
            list = candDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Unable to fetch candidate data.", e);
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Start of HTML
        out.println("<html>");
        out.println("<head>");
        ServletContext ctx2 = this.getServletContext();
        String bgcolor = ctx2.getInitParameter("background-color");
        out.printf("<body style='font-family: Arial, sans-serif; background-color: %s; margin: 20px;'>",bgcolor);
        out.println("<title>Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; line-height: 1.6; }");
        out.println("h1, h2 { text-align: center; color: #333; }");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }");
        out.println("th { background-color: #f4f4f4; }");
        out.println("a img { margin: 0 5px; }");
        out.println("p { text-align: center; margin-top: 20px; }");
        out.println("p a { text-decoration: none; color: blue; }");
        out.println("p a:hover { text-decoration: underline; }");
        out.println(".add-btn { display: block; width: 150px; margin: 20px auto; padding: 10px; text-align: center; color: white; background-color: #007BFF; text-decoration: none; border-radius: 5px; }");
        out.println(".add-btn:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Fetch application title from ServletContext
        ServletContext app = this.getServletContext();
        String appTitle = app.getInitParameter("app.title");
        out.printf("<h1>%s</h1>", appTitle);

        // Retrieve cookies
        Cookie[] cookies = req.getCookies();
        String userName = "Guest", role = "User";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("uname".equals(c.getName())) userName = c.getValue();
                if ("role".equals(c.getName())) role = c.getValue();
            }
        }
        out.printf("<p>Welcome, <b>%s</b> (%s)</p>", userName, role);

        // Display Announcement
        String announcement = (String) app.getAttribute("announcement");
        if (announcement != null) {
            out.printf("<p style='color:red;'>NOTE: %s</p>", announcement);
        }

        // "Add Candidate" button
        out.println("<a href='newCandidate.html' class='add-btn'>Add Candidate</a>");
        
        // Add User button
        out.println("<a href = 'newUser.html' class='add-btn'>Add User</a> ");

        // Display Table
        out.println("<h2>Voting Result</h2>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Party</th>");
        out.println("<th>Votes</th>");
        out.println("<th>Actions</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        // Populate table with candidates
        for (Candidate c : list) {
            out.println("<tr>");
            out.printf("<td>%d</td>", c.getId());
            out.printf("<td>%s</td>", c.getName());
            out.printf("<td>%s</td>", c.getParty());
            out.printf("<td>%d</td>", c.getVotes());
            out.printf("<td>"
                + "<a href='editcand?id=%d'><img src='images/edit.png' alt='Edit' width='40' height='40'></a>"
                + "        "
                + "<a href='delcand?id=%d'><img src='images/delete.png' alt='Delete' width='40' height='40'></a>"
                + "</td>",
                c.getId(), c.getId());
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");

        // Messages and Links
        String message = (String) req.getAttribute("msg");
        if (message != null) {
            out.printf("<p>%s</p>", message);
        }
        out.println("<p><a href='Announcement.html'>Make Announcement</a> | <a href='logout'>Sign Out</a></p>");

        // End of HTML
        out.println("</body>");
        out.println("</html>");
    }
}





