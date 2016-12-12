package kn_14_5_Holdobin.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import kn_14_5_Holdobin.User;
import kn_14_5_Holdobin.db.DaoFactory;
import kn_14_5_Holdobin.db.DatabaseException;

public class DeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4874138046557525107L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("okButton") != null) {
			doOk(req, resp);
			return;
		} else if (req.getParameter("cancelButton") != null) {
			doCancel(req, resp);
			return;
		}
		showPage(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/delete.jsp").forward(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = null;
		try {
			user = getUser(req);
		} catch (ValidationException e1) {
			req.setAttribute("error", e1.getMessage());
			req.getRequestDispatcher("/browse").forward(req, resp);
			return;
		}
		try {
			processUser(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		req.getRequestDispatcher("/browse").forward(req, resp);
	}

	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().delete(user);
	}

	/**
	 * @param req
	 * @return
	 * @throws ValidationException
	 */
	private User getUser(HttpServletRequest req) throws ValidationException {
		User user = new User();
		String idStr = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateStr = req.getParameter("dateOfBirth");

		if (firstName == null)
			throw new ValidationException("First name is empty");
		if (lastName == null)
			throw new ValidationException("Last name is empty");
		if (dateStr == null)
			throw new ValidationException("Date is empty");

		if (idStr != null) {
			user.setId(new Long(idStr));
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			user.setDateOfBirthd(DateFormat.getDateInstance().parse(dateStr));
		} catch (ParseException e) {
			throw new ValidationException("Date format is incorrect");
		}
		return user;
	}
}
