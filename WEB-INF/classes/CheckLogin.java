import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CheckLogin extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String logged = check(connection, login, password);
        System.out.println("check Login logged: " + logged);
        System.out.println("check Login login, password: " + login + " " + password);
        if (logged != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", logged);
            res.sendRedirect("menu");
        } else {
            PrintWriter toClient = res.getWriter();
            toClient.println(Utils.header("Login"));
            if (login != null) {
              toClient.println("<h1>Login incorrect</h1>");
            }
            toClient.println(loginForm());
            toClient.println(Utils.footer("Login"));
            toClient.close();
        }
    }
    
    String check(Connection connection, String login, String password) {
        String sql = "Select Firstname, Lastname FROM Employees";
        sql += " WHERE EmployeeID=? and Extension=?";
        System.out.println("check Login: " + sql);
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                name = result.getString("Firstname") + " " + result.getString("Lastname");
           }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in check login: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in check login. Exception: " + e);
        }
        return name;
    }

    String loginForm() {
        String out = "";
        out += "<form action='CheckLogin'";
        out += " method='GET' name='form'>";
        out += "<pre>";
        out += " Nombre:&nbsp;&nbsp;&nbsp;&nbsp; <input NAME='login' size='20'>\n";
        out += " Contrase&ntilde;a: <input TYPE='password' NAME='password' size='20'>\n";
        out += " <input TYPE='SUBMIT' VALUE='Login'>";
        out += "</pre>";
        out += "</form>";
        return out;
    }
}
