package apprepo;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AppDaoTest {
	Connection connection;
	AppDao 	appDao = new AppDao();
	
	@Test
	public void testConnection() {
	//	AppDao 	appDao = new AppDao();
		connection = appDao.getConnection();
		assertNotNull(connection);
	}

	@Test
	public void testGetAppData() {
		List<Application> applications = null;
		try {
			applications = AppDao.getAppData();
			assertTrue(applications.size()!=0);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			fail("Dao Threw exception");
		}
		
	}
	
	@Test
	public void testAddRecord() {
		Application app = new Application();
		app.setName("Test");
		String linksStr = "TestLink" ;
		String[] linksArray = linksStr.substring(1, linksStr.length() - 1).split(", ");
        app.setLinks(linksArray);
		assertTrue(appDao.addRecord(app));
		deleteTestRecord();
	}
	
	public void deleteTestRecord() {
		 Statement statement = null;
	     ResultSet resultSet = null;
	     Connection connection = null;
	     List<Application> applications = new ArrayList<>();
	 
	     // Establish the connection
	     try {
	         // AppDao appDao = new AppDao();
	         connection = appDao.getConnection();
	         statement = connection.createStatement();

	         // Define SQL query
	         String query = "DELETE FROM rentapplications.appinfo WHERE NAME = 'Test'";

	         // Execute query and get results
	         System.out.println(statement.executeUpdate(query));
	     }
	     catch(Exception e) {
	     	e.printStackTrace();
	     }
	     
	  }
}
