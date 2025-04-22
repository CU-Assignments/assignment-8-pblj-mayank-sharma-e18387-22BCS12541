import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SaveAttendanceServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_portal";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Teesha123$"; 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String studentId = request.getParameter("student_id");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            String query = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(studentId));
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3, status);

            int rows = ps.executeUpdate();

            out.println("<html><body>");
            if (rows > 0) {
                out.println("<h2>Attendance submitted successfully!</h2>");
            } else {
                out.println("<h2>Failed to submit attendance.</h2>");
            }
            out.println("</body></html>");

            ps.close();
            conn.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
