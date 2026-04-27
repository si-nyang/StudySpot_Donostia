import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class menu extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nickname");

        HttpSession session = request.getSession();
        session.setAttribute("nickname", nickname);

        response.sendRedirect("menu");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        String nickname = "";

        if (session != null) { // session exists
            Object n = session.getAttribute("nickname");
            if (n != null) nickname = n.toString();
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8' />");
        out.println("<title>Study Spots</title>");
        out.println("<link rel='stylesheet' href='styles.css' />");
        out.println("</head>");
        out.println("<body>");

        if (nickname.equals("")) {
            out.println("<script>");
            out.println("var name = prompt('Enter your nickname:');");
            out.println("if (name) {");
            out.println("  var form = document.createElement('form');");
            out.println("  form.method = 'POST';");
            out.println("  form.action = 'menu';");
            out.println("  var input = document.createElement('input');");
            out.println("  input.type = 'hidden';");
            out.println("  input.name = 'nickname';");
            out.println("  input.value = name;");
            out.println("  form.appendChild(input);");
            out.println("  document.body.appendChild(form);");
            out.println("  form.submit();");
            out.println("}");
            out.println("</script>");
        }

        out.println("<div class='page-wrap'>");
        out.println("<header class='topbar'>");
        out.println("<div class='container topbar-inner'>");
        out.println("<div class='brand'>");
        out.println("<h1>&#128205; Study Spots</h1>");
        out.println("<p>Study Spots in San Sebasti&aacute;n</p>");

        if (!nickname.equals("")) {
            out.println("<p>Hello, " + nickname + "!</p>");
        }

        out.println("</div>");
        out.println("</div>");
        out.println("</header>");

        out.println("<main class='container section'>");
        out.println("<h2 class='section-title'>San Sebasti&aacute;n Study Spots</h2>");
        out.println("<p class='muted'>Select an option below:</p>");
        out.println("<div class='index-grid' id='cardContainer'></div>");
        out.println("</main>");
        out.println("</div>");

        out.println("<script src='script.js'></script>");

        out.println("</body>");
        out.println("</html>");
    }
}
