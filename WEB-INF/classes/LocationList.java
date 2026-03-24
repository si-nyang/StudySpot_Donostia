import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class LocationList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String category = req.getParameter("category");
        toClient.println("<h1 align=\"center\"><a href=\"index.html\">Study Spots Donostia</a></h1>");
        toClient.println("<table border='1' align='center'>");
        toClient.println("<tr><td>Name</td><td>Rating</td><td>Category</td><td>Location</td><td>Photo</td></tr>");
        Vector<LocationData> locationList;
        if (category != null) {
            locationList = LocationData.getLocations(connection, category);
        } else {
            locationList = LocationData.getLocations(connection);
        }
        for(int i=0; i< locationList.size(); i++){
                LocationData location = locationList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + location.locationName + " </td>");
                toClient.println("<td>" + location.avgRating + " </td>");
                toClient.println("<td>" + location.category + " </td>");
                toClient.println("<td>" + location.address + " </td>");
                toClient.println("<td><img src='/images/1_1.jpg' alt='img'></td>");
                toClient.println("<td><a href='index.html'>" + "More Info</a></td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
        toClient.close();
    }
}