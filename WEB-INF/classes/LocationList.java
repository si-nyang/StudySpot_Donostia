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
        toClient.println("<h1 align=\"center\"><a href=\"index\">Study Spots Donostia</a></h1>");
        toClient.println("<table border='1' align='center'>");
        toClient.println("<tr><td>Name</td><td>Rating</td><td>Category</td><td>Location</td><td>Photo</td><td>More Info</td></tr>");
        Vector<LocationData> locationList;
        if (category != null && !category.isEmpty()) {
            locationList = LocationData.getLocationList(connection, category);
            System.out.print(locationList);
        } else {
            locationList = LocationData.getLocationList(connection);
        }

        System.out.println(locationList.size());
        toClient.println("<div id='categoryFilter' align='center'>");
        toClient.println("<form action='/StudySpot_Donostia/LocationList'>");
        toClient.println("<select id='category' name='category' size='4' multiple>");
        toClient.println("<option value='University'>University</option>");
        toClient.println("<option value='Cafe'>Cafe</option>");
        toClient.println("<option value='Library'>Library</option>");
        toClient.println("<option value=''>All</option>");
        toClient.println("</select>");
        toClient.println("<input type='submit' value='Submit'>");
        toClient.println("</form>");
        toClient.println("</div>");
        String context = req.getContextPath();


        for(int i=0; i< locationList.size(); i++){
                LocationData location = locationList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + location.locationName + " </td>");
                toClient.println("<td>" + location.avgRating + " </td>");
                toClient.println("<td>" + location.category + " </td>");
                toClient.println("<td>" + location.address + " </td>");
                toClient.println("<td><img src='" + context + "/images/"+ location.id +"_1.jpg' alt='image'></td>");
                toClient.println("<td><a href='LocationDetail?id=" + location.id + "'>Detail</a></td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
        toClient.close();
    }
}
