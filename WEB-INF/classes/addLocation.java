import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class addLocation extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String[] tags = req.getParameterValues("tags");
        String selectedTags = "";
        if (tags != null) {
            selectedTags = String.join(",", tags);
        }
        String lonParam = req.getParameter("lon");
        String latParam = req.getParameter("lat");
        Float lon = 0.0f;
        Float lat = 0.0f;
        if (lonParam != null && !lonParam.isEmpty()) {
            lon = Float.parseFloat(lonParam);
        }
        if (latParam != null && !latParam.isEmpty()) {
            lat = Float.parseFloat(latParam);
        }
        int photos = 0;

        LocationData location  = new LocationData(
                    req.getParameter("category"),
                    req.getParameter("name"),
                    req.getParameter("description"),
                    req.getParameter("address"),
                    req.getParameter("hours"),
                    selectedTags,
                    Double.parseDouble(req.getParameter("ratings")),
                    req.getParameter("Creator"),
                    lon,
                    lat,
                    photos
                   
        );
        int n = LocationData.InsertLocation(connection, location);

        res.sendRedirect("menu" );
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.close();
    }
}
