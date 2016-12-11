package kn_14_5_Holdobin.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kn_14_5_Holdobin.User;

public class BrowseServletTest3 extends MockServletTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	public void testBrowse() {
		User user = new User(1000L, "Arsenii", "Holdobin", new Date());
		List list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session", collection);
		assertSame(list, collection);
	}

}
