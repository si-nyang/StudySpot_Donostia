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
    double avgRating;
    int reviews;
    float lon;
    float lat;

    LocationData (int id, String category, String locationName, String address, double avgRating) {
        this.id = id;
        this.category = category;
        this.locationName = locationName;
        this.address= address;
        this.avgRating=avgRating;
    }
    
    LocationData (int id, String category, String locationName, String address, String description, String hours, int photos, String tags, double avgRating, int reviews, float lon, float lat) {
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

    LocationData (String category, String locationName, String address, String hours, String tags) {
        this.id = id;
        this.category = category;
        this.locationName = locationName;
        this.address= address;
        this.hours = hours;
        this.tags = tags;
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
    String sql = "SELECT ID, Category, LocationName, Address, AvgRating FROM Locations";
    System.out.println("getLocations: " + sql);

        try {
            System.out.println("before prepareStatement");
            PreparedStatement pstmt = connection.prepareStatement(sql);
    
            System.out.println("before executeQuery");
            ResultSet result = pstmt.executeQuery();
    
            System.out.println("after executeQuery");
    
            while(result.next()) {
                System.out.println("reading one row");
    
                LocationData location = new LocationData(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("Category"),
                    result.getString("LocationName"),
                    result.getString("Address"),
                    Double.parseDouble(result.getString("AvgRating"))
                );
                vec.addElement(location);
            }
    
            System.out.println("after while, rows=" + vec.size());
    
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error in getLocations: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static int InsertLocation(Connection connection, LocationData location) {
        String sql ="INSERT INTO Locations (Category, LocationName, Address, Hours, Tags) "
            + "VALUES (?, ?, ?)";
        System.out.println("updateLocation: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,location.Category);
			stmtUpdate.setString(2,location.LocationName);
            stmtUpdate.setString(3,location.Address);
            stmtUpdate.setString(4,location.Hours);
            stmtUpdate.setString(5,location.Tags);
			
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertLocation: " + sql + " Exception: " + e);
        }
        return n;
    }
}
