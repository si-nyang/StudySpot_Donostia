import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class UpdateLocation extends HttpServlet {
    
    private Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    // Display the edit form with current location data
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
        toClient.println("<title>Update Location</title>");
        toClient.println("<link rel='stylesheet' href='styles.css'>");
        toClient.println("</head>");
        toClient.println("<body>");

        toClient.println("<div class='page-wrap'>");
        toClient.println("<div class='container'>");

        toClient.println("<h1>Update Location</h1>");

        toClient.println("<form action='UpdateLocation' method='POST'>");
        
        // Hidden field for ID
        toClient.println("<input type='hidden' name='id' value='" + location.id + "'>");

        // Category
        toClient.println("<div class='form-group'>");
        toClient.println("<label for='category'>Category</label>");
        toClient.println("<select name='category' id='category'>");
        toClient.println("<option value='Restaurant'" + (location.category.equals("Restaurant") ? " selected" : "") + ">Restaurant</option>");
        toClient.println("<option value='Cafe'" + (location.category.equals("Cafe") ? " selected" : "") + ">Cafe</option>");
        toClient.println("<option value='Park'" + (location.category.equals("Park") ? " selected" : "") + ">Park</option>");
        toClient.println("<option value='Museum'" + (location.category.equals("Museum") ? " selected" : "") + ">Museum</option>");
        toClient.println("<option value='Shopping'" + (location.category.equals("Shopping") ? " selected" : "") + ">Shopping</option>");
        toClient.println("<option value='Entertainment'" + (location.category.equals("Entertainment") ? " selected" : "") + ">Entertainment</option>");
        toClient.println("</select>");
        toClient.println("</div>");

        // Name
        toClient.println("<div class='form-group'>");
        toClient.println("<label for='name'>Name</label>");
        toClient.println("<input type='text' name='name' id='name' value='" + escapeHtml(location.locationName) + "' required>");
        toClient.println("</div>");

        // Description
        toClient.println("<div class='form-group'>");
        toClient.println("<label for='description'>Description</label>");
        toClient.println("<textarea name='description' id='description' rows='4' required>" + escapeHtml(location.description) + "</textarea>");
        toClient.println("</div>");

        // Address
        toClient.println("<div class='form-group'>");
        toClient.println("<label for='address'>Address</label>");
        toClient.println("<input type='text' name='address' id='address' value='" + escapeHtml(location.address) + "' required>");
        toClient.println("</div>");

        // Hours
        toClient.println("<div class='form-group'>");
        toClient.println("<label for='hours'>Hours</label>");
        toClient.println("<input type='text' name='hours' id='hours' value='" + escapeHtml(location.hours) + "' required>");
        toClient.println("</div>");

        // Tags (checkboxes)
        toClient.println("<div class='form-group'>");
        toClient.println("<label>Tags</label>");
        toClient.println("<div class='checkbox-group'>");
        
        String[] availableTags = {"Family Friendly", "Pet Friendly", "Outdoor Seating", "WiFi", "Parking", "Wheelchair Accessible", "Live Music", "Takeout"};
        String currentTags = location.tags != null ? location.tags : "";
        
        for (String tag : availableTags) {
            boolean checked = currentTags.contains(tag);
            toClient.println("<label class='checkbox-label'>");
            toClient.println("<input type='checkbox' name='tags' value='" + tag + "'" + (checked ? " checked" : "") + "> " + tag);
            toClient.println("</label>");
        }
        toClient.println("</div>");
        toClient.println("</div>");

        // Rating
        toClient.println("<div class='form-group'>");
        toClient.println("<label for='ratings'>Rating</label>");
        toClient.println("<input type='number' name='ratings' id='ratings' min='0' max='5' step='0.1' value='" + location.avgRating + "' required>");
        toClient.println("</div>");

        // Submit buttons
        toClient.println("<div class='form-actions'>");
        toClient.println("<button type='submit' class='btn btn-primary'>Update Location</button>");
        toClient.println("<a href='LocationDetail?id=" + location.id + "' class='btn btn-secondary'>Cancel</a>");
        toClient.println("</div>");

        toClient.println("</form>");

        toClient.println("</div>");
        toClient.println("</div>");

        toClient.println("</body>");
        toClient.println("</html>");

        toClient.close();
    }

    // Process the form submission
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String[] tags = req.getParameterValues("tags");
        String selectedTags = "";
        if (tags != null) {
            selectedTags = String.join(",", tags);
        }

        LocationData location = new LocationData(
            req.getParameter("category"),
            req.getParameter("name"),
            req.getParameter("description"),
            req.getParameter("address"),
            req.getParameter("hours"),
            selectedTags,
            Double.parseDouble(req.getParameter("ratings"))
        );
        location.id = Integer.parseInt(id);

        int result = LocationData.updateLocation(connection, location);

        if (result > 0) {
            res.sendRedirect("LocationDetail?id=" + id);
        } else {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter toClient = res.getWriter();
            toClient.println("<h2>Error updating location</h2>");
            toClient.println("<a href='UpdateLocation?id=" + id + "'>Try again</a>");
            toClient.close();
        }
    }

    // Helper method to escape HTML special characters
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
    }
}

