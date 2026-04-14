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
        String tags = req.getParameter("tags")
        String selectedTags = ""; 
        if (tags != null) {
            selectedTags = String.join(",", tags);
        }
        LocationData location  = new LocationData(
                    req.getParameter("category"),
                    req.getParameter("name"),
                    req.getParameter("description"),
                    req.getParameter("address"),
                    req.getParameter("hours"),
                    selectedTags
        );
        int n = LocationData.InsertLocation(connection, location);

        res.sendRedirect("index.html" );
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.close();
    }
}
