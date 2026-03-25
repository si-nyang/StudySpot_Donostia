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

        String id = req.getParameter("id");

        toClient.println("<!DOCTYPE html><body>");
        toClient.println("<h1 align=\"center\"><a href=\"index.html\">Study Spots Donostia</a></h1>");
        toClient.println("<table border='1' align='center'>");

        LocationData location;
        location = LocationData.getLocation(connection, id);
        toClient.println("<tr><td>Name</td>");
        toClient.println("<td>"+location.locationName+"</td></tr>");
        toClient.println("<tr><td>Rating</td>");
        toClient.println("<td>"+location.avgRating+"</td></tr>");
        toClient.println("<tr><td>Category</td>");
        toClient.println("<td>"+location.category+"</td></tr>");
        toClient.println("<tr><td>Description</td>");
        toClient.println("<td>"+location.description+"</td></tr>");
        toClient.println("<tr><td>Address</td>");
        toClient.println("<td>"+location.address+"</td></tr>");
        toClient.println("<tr><td>Hours</td>");
        toClient.println("<td>"+location.hours+"</td></tr>");
        toClient.println("<tr><td>Tags</td>");
        toClient.println("<td>"+location.tags+"</td></tr>");
        toClient.println("<tr><td>Images</td>");
        toClient.println("<td><img src='/images/1_1.jpg' alt='img'></td>");
        toClient.println("</tr></table>");
        toClient.println("<a href='LocationList'>Go back to List</a>");
        toClient.println("</body>");
        toClient.close();
    }
}

