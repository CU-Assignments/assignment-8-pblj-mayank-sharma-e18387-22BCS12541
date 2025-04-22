import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/company";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Teesha123$";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            Statement stmt = conn.createStatement();

            ResultSet rs;
            if (idParam != null && !idParam.isEmpty()) {
                // Search by ID
                rs = stmt.executeQuery("SELECT * FROM employee WHERE id = " + idParam);
            } else {
                // Show all employees
                rs = stmt.executeQuery("SELECT * FROM employee");
            }

            out.println("<html><body>");
            out.println("<h2>Employee Directory</h2>");
            out.println("<form method='get'>Search by ID: <input type='text' name='id'><input type='submit' value='Search'></form>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>"
                        + rs.getString("name") + "</td><td>"
                        + rs.getString("department") + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
