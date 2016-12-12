package kn_14_5_Holdobin.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import kn_14_5_Holdobin.User;

public class DeleteServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(DeleteServlet.class);
	}

	@Test
	public void testDelete() {
		Date date = new Date();
		User user = new User(1000L, "Arsenii", "Holdobin", date);
		getMockUserDao().expectAndReturn("delete", user, user);
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Arsenii");
		addRequestParameter("lastName", "Holdobin");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}
	
}

