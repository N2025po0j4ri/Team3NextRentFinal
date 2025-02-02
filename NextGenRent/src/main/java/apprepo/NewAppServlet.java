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
import java.util.List;

/**
 * Servlet implementation class FilterServlet
 */
public class NewAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletConfig configNextGen; 
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		configNextGen =  config; 
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		
		return configNextGen;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String organization = request.getParameter("organization");
		String platforms = request.getParameter("platforms");
		String[] linksArray = request.getParameter("links").split(", ");
		String price = request.getParameter("price");
		Application app = new Application();
		app.setName(name);
		app.setDescription(description);
		app.setOrganization(organization);
		app.setPlatforms(platforms);	
		app.setLinks(linksArray);
		app.setPrice(price);
		boolean isAdded = AppDao.addRecord(app);
		if(isAdded) {
			List<Application> appList = (List<Application>) request.getSession().getAttribute("applications");
			appList.add(app);
			request.getSession().setAttribute("applications", appList);
		}
	    // Forward to JSP to display the data
	    request.getRequestDispatcher("/displayApplications.jsp").forward(request, response);
		
	}

	public static String getExternalLink() {
		// TODO Auto-generated method stub
		return null;
	}

}
