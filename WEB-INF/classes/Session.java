import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Session extends HttpServlet {

  public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession(true);
    String nick = (String) session.getAttribute("nickname");

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>Add Location</title>");
    out.println("<link rel='stylesheet' href='styles.css' />");
    out.println("</head>");
    out.println("<body>");

    out.println("<header class='topbar'>");
    out.println("<div class='container topbar-inner'>");
    out.println("<div class='brand'>");
    out.println("<h1 align='center'><a href='menu'>Study Spots Donostia</a></h1>");
    out.println("</div></div></header>");

    out.println("<main class='section'><div class='container'>");
    out.println("<div class='info-box'><h2 class='section-title'>Add Location</h2>");

    out.println("<form action='addLocation' method='GET'>");

    // Session name
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Creator</label>");
    out.println("<input type='text' name='Creator' style='width:100%;padding:12px;' value='"+nick+"' readonly>");
    out.println("</div>");

    // Category
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Category</label>");
    out.println("<input type='text' name='category' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // Name
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Name</label>");
    out.println("<input type='text' name='name' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // Description
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Description</label>");
    out.println("<input type='text' name='description' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // Address
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Address</label>");
    out.println("<input type='text' name='address' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // Hours
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Hours</label>");
    out.println("<input type='text' name='hours' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // Tags
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Tags</label>");
    out.println("<div>");

    String[] tags = {"quiet","wifi","library","coffee","laptop","food","cozy"};
    for (String tag : tags) {
        out.println("<label><input type='checkbox' name='tags' value='" + tag + "'> " + tag + "</label><br>");
    }

    out.println("</div></div>");

    // Ratings
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Ratings</label>");
    out.println("<input type='text' name='ratings' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // longitude
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Longitude</label>");
    out.println("<input type='text' name='lon' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // latitude
    out.println("<div style='margin-bottom:20px;'>");
    out.println("<label class='eyebrow'>Latitude</label>");
    out.println("<input type='text' name='lat' style='width:100%;padding:12px;'>");
    out.println("</div>");

    // Submit
    out.println("<div style='margin-top:30px;'>");
    out.println("<input type='submit' value='Submit' style='width:100%;'>");
    out.println("</div>");

    out.println("</form>");
    out.println("</div></div></main>");

    out.println("</body></html>");  
    }
}