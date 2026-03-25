import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/index", ""})
public class index extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); 
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8' />");
        out.println("  <title>Study Spots</title>");
        out.println("  <link rel='stylesheet' href='styles.css' />");
        out.println("</head>");
        out.println("<body>");
        
        out.println("  <div class='page-wrap'>");
        out.println("    <header class='topbar'>");
        out.println("      <div class='container topbar-inner'>");
        out.println("        <div class='brand'>");
        out.println("          <h1>&#128205Study Spots</h1>");
        out.println("          <p>Study Spots in San Sebasti&aacuten</p>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("    </header>");

        out.println("    <main class='container section'>");
        out.println("      <h2 class='section-title'>San Sebasti&aacuten Study Spots</h2>");
        out.println("      <p class='muted'>Select an option below:</p>");

        // Using the grid system and cards to match your mockup
        out.println("      <div class='index-grid'>");

        // Card 1: List Locations
        out.println("        <a class='preview-card' href='LocationList'>");
        out.println("          <div class='preview-body'>");
        out.println("            <h3>1. List Page</h3>");
        out.println("            <p>View all available study spots.</p>");
        out.println("            <span class='cta-btn' style='background-color: #1a2a40; color: white; padding: 10px; display: inline-block; border-radius: 4px; text-decoration: none;'>Open page</span>");
        out.println("          </div>");
        out.println("        </a>");

        // Card 2: Map
        out.println("        <a class='preview-card' href='Map.html'>");
        out.println("          <div class='preview-body'>");
        out.println("            <h3>2. Map Page</h3>");
        out.println("            <p>See spots on the map.</p>");
        out.println("            <span class='cta-btn' style='background-color: #1a2a40; color: white; padding: 10px; display: inline-block; border-radius: 4px; text-decoration: none;'>Open page</span>");
        out.println("          </div>");
        out.println("        </a>");

        // Card 3: Add Location
        out.println("        <a class='preview-card' href='AddLocation.html'>");
        out.println("          <div class='preview-body'>");
        out.println("            <h3>3. Add Location</h3>");
        out.println("            <p>Share a new study spot.</p>");
        out.println("            <span class='cta-btn' style='background-color: #1a2a40; color: white; padding: 10px; display: inline-block; border-radius: 4px; text-decoration: none;'>Open page</span>");
        out.println("          </div>");
        out.println("        </a>");

        out.println("      </div>"); // End index-grid
        out.println("    </main>");
        out.println("  </div>");
        out.println("</body>");
        out.println("</html>");
    }
}