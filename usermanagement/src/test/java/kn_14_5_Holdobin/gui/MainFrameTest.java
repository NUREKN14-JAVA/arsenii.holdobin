package kn_14_5_Holdobin.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.zip.DataFormatException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import kn_14_5_Holdobin.db.DaoFactory;
import kn_14_5_Holdobin.db.DaoFactoryImpl;
import kn_14_5_Holdobin.util.Messages;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		Properties properties= new Properties();
		properties.setProperty("dao.kn_14_5_Holdobin.usermanagement.db.UserDao", MockUserDao.class.getName());
		properties.setProperty("dao.factory", DaoFactoryImpl.class.getName());
		DaoFactory.init(properties);
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	@After
	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);
		super.tearDown();
	}
	
	private Component find(Class componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name +"'", component);
		return component;
	}
	
	@Test
	public void testBrowseControls() {
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals(Messages.getString("UserTableModel.id"), table.getColumnName(0));
		assertEquals(Messages.getString("UserTableModel.first_name"), table.getColumnName(1));
		assertEquals(Messages.getString("UserTableModel.last_name"), table.getColumnName(2));
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
		
	}
	
	@Test
	public void testAddUser() {
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(0, table.getRowCount());
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		find(JPanel.class, "addPanel");
		JTextField firstNameField =  (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
		JButton cancelButton = (JButton) find(JButton.class, "cancelButton");

		getHelper().sendString(new StringEventData(this, firstNameField, "Arsenii"));
		getHelper().sendString(new StringEventData(this, lastNameField, "Holdibin"));
		DateFormat formatter = DateFormat.getInstance();
		String date = formatter.format(new Date());
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
	}

}
