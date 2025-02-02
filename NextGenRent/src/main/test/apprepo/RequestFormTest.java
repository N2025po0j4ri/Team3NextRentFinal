package apprepo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class RequestFormTest {

    private NewAppServlet requestForm;

    @Before
    public void setUp() {
        requestForm = new NewAppServlet(); // Initialize the request form object
    }

    @Test
    public void testGetExternalLink() {
        // Expected external link (for demonstration purposes, use a dummy URL)
        String expectedLink = "http://example.com/applicationPage";

        // Call the method that is supposed to return the external link
        String actualLink = NewAppServlet.getExternalLink();

        // Assertions
        assertNotNull("The external link should not be null", actualLink);
        assertEquals("The external link does not match the expected URL", expectedLink, actualLink);
    }
}
