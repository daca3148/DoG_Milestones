package servlets;

import beans.User;
import sql.UserSql;
import util.passwordHashUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loggedOut = request.getParameter("logOut");

		if (loggedOut != null && loggedOut.equals("logOut")) {
			HttpSession session = request.getSession();
			session.setAttribute("User", null);
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String salt = "ssww112asda32sssa4";

		password = passwordHashUtil.getMd5(salt + password);

		UserSql sql = new UserSql();

		User user = sql.readByUsernameAndPassword(username, password);

		if (user != null) {

			HttpSession session = request.getSession();
			session.setAttribute("User", user);

			response.sendRedirect("/profile");
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
