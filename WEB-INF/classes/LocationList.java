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
        String category = req.getParameter("category");
        String ajax = req.getParameter("ajax");

        Vector<LocationData> locationList;
        if (category != null && !category.isEmpty()) {
            locationList = LocationData.getLocationList(connection, category);
            System.out.print(locationList);
        } else {
            locationList = LocationData.getLocationList(connection);
        }
        PrintWriter toClient = res.getWriter();
        String context = req.getContextPath();


        if (ajax != null) {
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
        }
        else {

            toClient.println("<head><link rel='stylesheet' href='styles.css'/></head>");
            toClient.println("<body><h1 id='locationListHeader' align=\"center\"><a href=\"menu\">Study Spots Donostia</a></h1>");
        

            System.out.println(locationList.size());
            toClient.println("<div id='categoryFilter' align='right'>");
            toClient.println("<form>");
            toClient.println("<select id='category' name='category' size='4' multiple>");
            toClient.println("<option value='University'>University</option>");
            toClient.println("<option value='Cafe'>Cafe</option>");
            toClient.println("<option value='Library'>Library</option>");
            toClient.println("<option value=''>All</option>");
            toClient.println("</select>");
            toClient.println("<br><input type='button' value='Apply' onclick='applyFilter()'>");
            toClient.println("</form>");
            toClient.println("</div>");
            toClient.println("<table border='1' align='center' id='locationTable'>");
            toClient.println("<tr><td>Name</td><td>Rating</td><td>Category</td><td>Location</td><td>Photo</td><td>More Info</td></tr>");


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
            toClient.println("<script src='filter.js'></script></body>");
    }
        toClient.close();
    }
}
