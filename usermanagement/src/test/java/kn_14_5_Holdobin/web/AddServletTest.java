package kn_14_5_Holdobin.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import kn_14_5_Holdobin.User;

public class AddServletTest extends MockServletTestCase {

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	@Test
	public void testAdd() {
		Date date = new Date();
		User newUser = new User("Arsenii", "Holdobin", date);
		User user = new User(1000L, "Arsenii", "Holdobin", date);
		getMockUserDao().expectAndReturn("create", user, user);
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Arsenii");
		addRequestParameter("lastName", "Holdobin");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	@Test
	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("lastName", "Holdobin");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testAddEmptyLastName() {
		Date date = new Date();
		addRequestParameter("firstName", "Arsenii");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testAddEmptyDateOfBirth() {
		Date date = new Date();
		addRequestParameter("firstName", "Arsenii");
		addRequestParameter("lastName", "Holdobin");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testAddEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("firstName", "Arsenii");
		addRequestParameter("lastName", "Holdobin");
		addRequestParameter("dateOfBirth", "sda");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}
