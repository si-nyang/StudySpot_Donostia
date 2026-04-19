import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class mapJson extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("application/json; charset=UTF-8");
        PrintWriter toClient = res.getWriter();
       
        Vector<LocationData> locationList;
        locationList = LocationData.getLocationListForMap(connection);

        toClient.println("[");

        for(int i=0; i< locationList.size(); i++){
                LocationData location = locationList.elementAt(i);

                toClient.println("{");
                toClient.println("\"name\":\"" + location.locationName + "\",");
                toClient.println("\"description\":\"" + location.description + "\",");
                toClient.println("\"longitude\":" + location.lon + ",");
                toClient.println("\"latitude\":" + location.lat);
                toClient.println("}");

                if(i < locationList.size() - 1) toClient.println(",");
        }
        
        toClient.println("]");
        toClient.close();
    }
}
