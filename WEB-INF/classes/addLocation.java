import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CustomerInsert extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String selectedTags = req.querySelectorAll("input[name='tags']:checked");
        selectedTags = Array.from(selectedTags).map(tag->tag.value.join(","));
        LocationData  = LocationData(
                    req.getParameter("name"),
                    req.getParameter("description"),
                    req.getParameter("address"),
                    req.getParameter("hours"),
                    selectedTags
        );
        int n = CustomerData.insertLocation(connection, customer);

        res.sendRedirect("LocationList.html" );
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.close();
    }
}