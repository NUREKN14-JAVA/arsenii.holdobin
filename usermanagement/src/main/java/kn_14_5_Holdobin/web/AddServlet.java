package kn_14_5_Holdobin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kn_14_5_Holdobin.User;
import kn_14_5_Holdobin.db.DaoFactory;
import kn_14_5_Holdobin.db.DatabaseException;

public class AddServlet extends EditServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6616393891034692288L;

	@Override
	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
		return;
	}

}
