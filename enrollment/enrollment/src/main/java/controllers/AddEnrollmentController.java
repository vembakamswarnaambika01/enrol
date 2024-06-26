package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEnrollmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddEnrollmentController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "chinnu");

            String courseCode = request.getParameter("courseCode");
            String studentID = request.getParameter("studentID");
            String courseName = request.getParameter("courseName");

            String sql = "INSERT INTO Enrollment (course_code, student_id, course_name) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, courseCode);
            statement.setString(2, studentID);
            statement.setString(3, courseName);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("index.html");
            }

            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
