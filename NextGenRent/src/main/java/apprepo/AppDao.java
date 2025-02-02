package apprepo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AppDao {
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/rentapplications";
        String user = "customer";
        String password = "password";
        Connection connection = null;
        // Load and register MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
            return null;
        }

        // Establish the connection
        try {
        	
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
                
            }
        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
        return connection;
	}
	
	public static boolean addRecord(Application app) {
		boolean ret = false;
	
        PreparedStatement pstmt = null;
        
        String sql = "INSERT INTO appinfo (Name, Description, Organization, Platforms, Links, Price) VALUES (?, ?, ?, ?, ?, ?)";

        
        Connection connection = null;
        try {
        	
           	connection = getConnection();
         
         // Execute query and get results
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,app.getName());
            pstmt.setString(2,app.getDescription());
            pstmt.setString(3,app.getOrganization());
            pstmt.setString(4,app.getPlatforms());
            pstmt.setString(5,app.getLinks().toString());
            pstmt.setString(6,app.getPrice());
            
         // Executing the INSERT statement
            pstmt.executeUpdate();
            ret = true;
           
        }
        catch(Exception e) {
        	ret = false;
        	e.printStackTrace();
        }

        return ret; 
		
	}
	public static List<Application> getAppData() throws ClassNotFoundException {
	
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Application> applications = new ArrayList<>();
        
        try {
        	
           	connection = getConnection();
            statement = connection.createStatement();

            // Define SQL query
            String query = "SELECT * FROM rentapplications.appinfo";

            // Execute query and get results
            resultSet = statement.executeQuery(query);
            
         
            // Process the results
            while (resultSet.next()) {
            	Application app = new Application();
                app.setName(resultSet.getString("Name"));
                app.setDescription(resultSet.getString("Description"));
                app.setOrganization(resultSet.getString("Organization"));
                app.setPlatforms(resultSet.getString("Platforms"));
                String linksStr = resultSet.getString("Links");
                String[] linksArray = linksStr.substring(1, linksStr.length() - 1).split(", ");
                app.setLinks(linksArray);
                app.setPrice(resultSet.getString("Price"));
                applications.add(app);
            }

        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    return applications;
	}	

}
