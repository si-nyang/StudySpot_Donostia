import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationData {
    int id;
    String category;
    String locationName;
    String address;
    String description;
    String hours;
    int photos;
    String tags;
    int avgRating;
    int reviews;
    float lon;
    float lat;

    
    LocationData (int id, String category, String locationName, String address, String description, String hours, int photos, String tags, int avgRating, int reviews, float lon, float lat) {
        this.id = id;
        this.category = category;
        this.locationName = locationName;
        this.address= address;
        this.description=description;
        this.hours=hours;
        this.photos=photos;
        this.tags=tags;
        this.avgRating=avgRating;
        this.reviews=reviews;
        this.lon=lon;
        this.lat=lat;
    }

    public static Vector<LocationData> getLocations(Connection connection, String category) {
        Vector<LocationData> vec = new Vector<LocationData>();
        String sql = "SELECT ID, Category, LocationName, Address, Description, Hours , Photos, Tags, AvgRating, Reviews, Longitude, Latitude FROM Locations";
        sql += " WHERE Category=?";
        System.out.println("getLocations: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, category);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                LocationData location = new LocationData(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("Category"),
                    result.getString("LocationName"),
                    result.getString("Address"),
                    result.getString("Description"),
                    result.getString("Hours"),
                    Integer.parseInt(result.getString("Photos")),
                    result.getString("Tags"),
                    Integer.parseInt(result.getString("AvgRating")),
                    Integer.parseInt(result.getString("Reviews")),
                    Float.parseFloat(result.getString("Longitude")),
                    Float.parseFloat(result.getString("Latitude"))
                );
                vec.addElement(location);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getLocations: " + sql + " Exception: " + e);
        }
        return vec;
    }
    public static Vector<LocationData> getLocations(Connection connection) {
        Vector<LocationData> vec = new Vector<LocationData>();
        String sql = "SELECT ID, Category, LocationName, Address, Description, Hours , Photos, Tags, AvgRating, Reviews, Longitude, Latitude FROM Locations";
        System.out.println("getLocations: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                LocationData location = new LocationData(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("Category"),
                    result.getString("LocationName"),
                    result.getString("Address"),
                    result.getString("Description"),
                    result.getString("Hours"),
                    Integer.parseInt(result.getString("Photos")),
                    result.getString("Tags"),
                    Integer.parseInt(result.getString("AvgRating")),
                    Integer.parseInt(result.getString("Reviews")),
                    Float.parseFloat(result.getString("Longitude")),
                    Float.parseFloat(result.getString("Latitude"))
                );
                vec.addElement(location);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getLocations: " + sql + " Exception: " + e);
        }
        return vec;
    }
   
}
