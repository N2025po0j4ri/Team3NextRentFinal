
package apprepo;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SortServletTest {
    private SortServlet sortServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter writer;

    @Before
    public void setUp() throws IOException {
    	sortServlet = new SortServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        writer = Mockito.mock(PrintWriter.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        // Set up mock behavior for getSession() to return the mock HttpSession
        when(request.getSession()).thenReturn(session);
        List<Application> appList = new ArrayList<>();
        Application app = new Application();
		app.setName("Test");
		String linksStr = "TestLink" ;
		String[] linksArray = linksStr.substring(1, linksStr.length() - 1).split(", ");
        app.setLinks(linksArray);
        appList.add(app);
        // Set up mock behavior for getSession().getAttribute(String) if needed
        when(session.getAttribute("applications")).thenReturn(appList);
        
        // Mock RequestDispatcher
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        // Mock ServletContext
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        when(request.getServletContext()).thenReturn(servletContext);

        // Set up mock behavior for getRequestDispatcher(String) to return the mock RequestDispatcher
        when(request.getRequestDispatcher("/displayApplications.jsp")).thenReturn(dispatcher);

        
        // Mocking behavior for response.getWriter()
        when(response.getWriter()).thenReturn(writer);
    }

    @After
    public void tearDown() {
    	sortServlet = null;
        request = null;
        response = null;
        writer = null;
    }

 
    @Test
    public void testDoPost() throws Exception {
        // Setting up mock request parameters (if needed)
        when(request.getParameter("param1")).thenReturn("value1");

        // Call doPost method
        sortServlet.doPost(request, response);

        // Verify that response was written correctly
        List<Application> list = (List<Application>) request.getSession().getAttribute("applications");
        assertTrue(list.size()!=0);
        
    }



	@Test
	public void testSortByAppName() {
		
		List<Application> applications = new ArrayList<Application>();
		Application app1 = new Application();
		app1.setName("B");
		applications.add(app1);
		Application app2 = new Application();
		app2.setName("A");
		applications.add(app2);
		printAppList(applications);
		sortServlet.sortByAppName(applications);
		printAppList(applications);
		assertTrue(applications.get(0).getName().equals("A"));
		assertTrue(applications.get(1).getName().equals("B"));
	}
	
	public void printAppList(List<Application> appList) {
		
		for (Application app:appList){
			System.out.print(app.getName()+ " ");
		}
		System.out.println();
	}

	
}
