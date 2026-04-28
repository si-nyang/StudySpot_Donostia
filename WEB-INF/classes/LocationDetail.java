import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class LocationDetail extends HttpServlet {

    private Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter toClient = res.getWriter();

        String id = req.getParameter("id");
        LocationData location = LocationData.getLocation(connection, id);

        if (location == null) {
            toClient.println("<h2>Location not found</h2>");
            return;
        }

        toClient.println("<!DOCTYPE html>");
        toClient.println("<html>");
        toClient.println("<head>");
        toClient.println("<meta charset='UTF-8'>");
        toClient.println("<title>Location Detail</title>");
        toClient.println("<link rel='stylesheet' href='styles.css'>");
        toClient.println("</head>");
        toClient.println("<body>");

        toClient.println("<header class='topbar'>");
        toClient.println("<div class='container topbar-inner'>");
        toClient.println("<div class='brand'>");
        toClient.println("<h1 align='center'><a href='menu'>Study Spots Donostia</a></h1>");
        toClient.println("</div></div></header>");

        toClient.println("<div class='page-wrap'>");

        // HERO
        toClient.println("<div class='detail-hero'>");
        toClient.println("<div class='detail-overlay'></div>");
        toClient.println("<div class='container detail-hero-content'>");

        toClient.println("<div class='detail-title-block'>");
        toClient.println("<span class='badge'>" + location.category + "</span>");
        toClient.println("<h1>" + location.locationName + "</h1>");
        toClient.println("<div class='rating'>Rating: " + location.avgRating + "</div>");
        toClient.println("<div class='rating'>Creator: " + location.creator + "</div>");

        toClient.println("</div>");

        toClient.println("</div>");
        toClient.println("</div>");

        // CONTENT
        toClient.println("<div class='detail-content'>");
        toClient.println("<div class='container'>");

        toClient.println("<p class='detail-lead'>" + location.description + "</p>");


        toClient.println("<div class='info-grid'>");

        toClient.println("<div class='info-box'>");
        toClient.println("<div class='eyebrow'>Address</div>");
        toClient.println("<strong>" + location.address + "</strong>");
        toClient.println("</div>");

        toClient.println("<div class='info-box'>");
        toClient.println("<div class='eyebrow'>Hours</div>");
        toClient.println("<strong>" + location.hours + "</strong>");
        toClient.println("</div>");

        toClient.println("</div>");

        // TAGS
        toClient.println("<div class='expect-grid'>");
        if (location.tags != null) {
            String[] tags = location.tags.split(",");
            for (String tag : tags) {
                toClient.println("<span class='expect-pill'>" + tag.trim() + "</span>");
            }
        }
        toClient.println("</div>");

        // IMAGE
        String context = req.getContextPath();
        
        // TODO: gallery css modify
        toClient.println("<div class='gallery'>");
        toClient.println("<div class='gallery-card'>");
        toClient.println("<td><img src='" + context + "/images/"+ location.id +"_1.jpg' alt='image'></td>");
        toClient.println("</div>");
        toClient.println("</div>");

        toClient.println("<a class='full-width-link' href='UpdateLocation?id="+location.id+"'>Update information</a>");

        toClient.println("</div>");
        toClient.println("</div>");

        toClient.println("</div>");

        toClient.println("</body>");
        toClient.println("</html>");

        toClient.close();
    }
}