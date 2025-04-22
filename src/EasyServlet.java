import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EasyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String validUser = "admin";
        String validPass = "password123";

        if (username.equals(validUser) && password.equals(validPass)) {
            out.println("<html><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2>Login Failed. Invalid credentials.</h2>");
            out.println("</body></html>");
        }
    }
}
