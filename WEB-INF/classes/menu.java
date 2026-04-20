import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/menu", ""})
public class menu extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); 
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8' />");
        out.println("  <meta name='viewport' content='width=device-width, initial-scale=1.0' />");
        out.println("  <title>Study Spots</title>");
        out.println("  <link rel='stylesheet' href='styles.css' />");
        out.println("</head>");
        out.println("<body>");
        
        out.println("  <div class='page-wrap'>");
        out.println("    <header class='topbar'>");
        out.println("      <div class='container topbar-inner'>");
        out.println("        <div class='brand'>");
        out.println("          <h1>&#128205; Study Spots</h1>");
        out.println("          <p>Study Spots in San Sebasti&aacuten</p>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("    </header>");

        out.println("    <main class='container section'>");
        out.println("      <h2 class='section-title'>San Sebasti&aacuten Study Spots</h2>");
        out.println("      <p class='muted'>Select an option below:</p>");

        out.println("      <div class='index-grid' id='cardContainer'></div>");

        out.println("    </main>");
        out.println("  </div>");

        out.println("  <script src='script.js'></script>");
        out.println("</body>");
        out.println("</html>");
    }
}
