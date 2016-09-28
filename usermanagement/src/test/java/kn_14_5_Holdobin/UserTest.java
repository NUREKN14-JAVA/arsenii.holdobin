package kn_14_5_Holdobin;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	private User user;
	private Date dateOfBirthd;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		user = new User();
		Calendar calendar= Calendar.getInstance();
		calendar.set(1996, Calendar.NOVEMBER, 30);
		dateOfBirthd = calendar.getTime();
	}
	
	@Test
	public void testGetFullName() {
		user.setFirstName("Arsenii");
		user.setLastName("Holdobin");
		assertEquals("Holdobin, Arsenii", user.getFullName());
	}

	public void testGetAge() {
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(2016-1996, user.getAge());
	}
}
