import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class LocationDetail extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println("<!DOCTYPE html><body>");
        toClient.println("<h1 align='center'><a href='index.html'>Study Spots Donostia</a></h1>");
        toClient.println("<table border='1' align='center'>");
        toClient.println("<tr><td>Name</td><td>jds</td></tr>");
        toClient.println("<tr><td>Description</td><td>4.5</td></tr>");
        toClient.println("<tr><td>Address</td><td>123 kni road Image</td></tr>");
        toClient.println("<tr><td>Hours</td><td>9AM-10PM</td></tr>");
        toClient.println("<tr><td>Tags (quiet, WiFi)</td><td></td>");
        toClient.println("<tr><td>Images</td><td><img src='images/1_1.jpg' alt'Image'></td>");
        toClient.println("</tr></table></body>");
        toClient.close();
    }
}

