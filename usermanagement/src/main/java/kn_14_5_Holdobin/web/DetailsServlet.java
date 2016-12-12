package kn_14_5_Holdobin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kn_14_5_Holdobin.User;
import kn_14_5_Holdobin.db.DatabaseException;

public class DetailsServlet extends DeleteServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8844076808095443249L;

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/details.jsp").forward(req, resp);
		return;
	}

	@Override
	protected void processUser(User user) throws DatabaseException {
	}

}
