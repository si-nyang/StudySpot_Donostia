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
    int photos; // # of photos
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

    LocationData (int id, String locationName, double avgRating, String category, String description, String address,
                    String hours, String tags, int photos) {
        this.id = id;
        this.locationName = locationName;
        this.avgRating=avgRating;
        this.category = category;
        this.description = description;
        this.address= address;
        this.hours = hours;
        this.tags = tags;
        this.photos = photos;
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

    LocationData (String category, String locationName, String description, String address, String hours, String tags, double rating) {
        this.category = category;
        this.locationName = locationName;
		this.description = description;
        this.address= address;
        this.hours = hours;
        this.tags = tags;
		this.avgRating = rating;
    }

    LocationData (String locationName, String description, float lon, float lat) {
        this.locationName = locationName;
        this.description = description;
		this.lon = lon;
        this.lat= lat;
    }

    /*
    public static Vector<LocationData> getLocationList(Connection connection, String category) {
        Vector<LocationData> vec = new Vector<LocationData>();
        String sql = "SELECT ID, Category, LocationName, Address, Description, Hours , Photos, Tags, AvgRating, Reviews, Longitude, Latitude FROM Locations";
        sql += " WHERE Category=?";
        System.out.println("getLocationList: " + sql);
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
                    Double.parseDouble(result.getString("AvgRating")),
                    Integer.parseInt(result.getString("Reviews")),
                    Float.parseFloat(result.getString("Longitude")),
                    Float.parseFloat(result.getString("Latitude"))
                );
                vec.addElement(location);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getLocationList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    */

    public static Vector<LocationData> getLocationList(Connection connection, String category) {
        Vector<LocationData> vec = new Vector<LocationData>();
        String sql = "SELECT ID, Category, LocationName, Address, AvgRating FROM Locations";
        sql += " WHERE Category=?";
        System.out.println("getLocationList: " + sql);
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
                    Double.parseDouble(result.getString("AvgRating"))
                );
                vec.addElement(location);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getLocationList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static Vector<LocationData> getLocationList(Connection connection) {
        Vector<LocationData> vec = new Vector<LocationData>();
        String sql = "SELECT ID, Category, LocationName, Address, AvgRating FROM Locations";
        System.out.println("getLocationList: " + sql);

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
            System.out.println("Error in getLocationList: " + sql + " Exception: " + e);
        }
        return vec;
    }
   
    public static Vector<LocationData> getLocationListForMap(Connection connection) {
        Vector<LocationData> vec = new Vector<LocationData>();

        String sql = "SELECT LocationName, Description, Longitude, Latitude FROM Locations";
        System.out.println("getLocationListForMap: " + sql);

        try {
            System.out.println("before prepareStatement");
            PreparedStatement pstmt = connection.prepareStatement(sql);

            System.out.println("before executeQuery");
            ResultSet result = pstmt.executeQuery();
    
            System.out.println("after executeQuery");
    
            while(result.next()) {
                System.out.println("reading one row");
    
                LocationData location = new LocationData(
                    result.getString("LocationName"),
                    result.getString("Description"),
                    Float.parseFloat(result.getString("Longitude")),
                    Float.parseFloat(result.getString("Latitude"))                    
                );
                vec.addElement(location);
            }
            System.out.println("after while, rows=" + vec.size());

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error in getLocationListForMap: " + sql + " Exception: " + e);
        }
        return vec;
    }

    public static LocationData getLocation(Connection connection, String id) {
        String sql = "SELECT ID, Category, LocationName, Address, Description, Hours , Photos, Tags, AvgRating FROM Locations";
        sql += " WHERE ID=?";

        LocationData location = null;;
        System.out.println("getLocation: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                location = new LocationData(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("LocationName"),
                    Double.parseDouble(result.getString("AvgRating")),
                    result.getString("Category"),
                    result.getString("Description"),
                    result.getString("Address"),
                    result.getString("Hours"),
                    result.getString("Tags"),
                    Integer.parseInt(result.getString("Photos"))
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getLocation: " + sql + " Exception: " + e);
        }
        return location;
    }
    
    public static int InsertLocation(Connection connection, LocationData location) {
        String sql ="INSERT INTO Locations (Category, LocationName, Description, Address, Hours, Tags, AvgRating) "
            + "VALUES (?, ?, ?, ?, ?,?,?)";
        System.out.println("updateLocation: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,location.category);
			stmtUpdate.setString(2,location.locationName);
            stmtUpdate.setString(3,location.description);
            stmtUpdate.setString(4,location.address);
            stmtUpdate.setString(5,location.hours);
            stmtUpdate.setString(6,location.tags);
            stmtUpdate.setDouble(7,location.avgRating);			
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertLocation: " + sql + " Exception: " + e);
        }
        return n;
    }
public static int updateLocation(Connection connection, LocationData location) {
    int result = 0;
    // Hier habe ich die Spaltennamen an dein SELECT-Statement angepasst (LocationName, AvgRating)
    String sql = "UPDATE Locations SET Category=?, LocationName=?, Description=?, Address=?, Hours=?, Tags=?, AvgRating=? WHERE ID=?";
    
    System.out.println("Executing updateLocation for ID: " + location.id);
    
    try {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, location.category);
        pstmt.setString(2, location.locationName);
        pstmt.setString(3, location.description);
        pstmt.setString(4, location.address);
        pstmt.setString(5, location.hours);
        pstmt.setString(6, location.tags);
        pstmt.setDouble(7, location.avgRating);
        pstmt.setInt(8, location.id);
        
        result = pstmt.executeUpdate();
        pstmt.close();
        
        System.out.println("Rows affected: " + result);
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error in updateLocation: " + sql + " Exception: " + e);
    }
    return result;
}
}
