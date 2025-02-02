package apprepo;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Servlet implementation class FilterServlet
 */
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		List<Application> appList = (List<Application>) request.getSession().getAttribute("applications");
		request.getSession().setAttribute("applications", sortByAppName(appList));
	    // Forward to JSP to display the data
	    request.getRequestDispatcher("/displayApplications.jsp").forward(request, response);
		
	}
	
	public List<Application> sortByAppName(List<Application> appList){
	
		// Sorting by name using a custom comparator
	    Collections.sort(appList, new Comparator<Application>() {
	        public int compare(Application app1, Application app2) {
	            return app1.getName().compareTo(app2.getName());
	        }
	    });
		return appList;
	}

}
