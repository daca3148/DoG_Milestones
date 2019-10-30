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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class signupServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/signUp.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		String name = firstName + " " + lastName;

		String salt = "ssww112asda32sssa4";

		password = passwordHashUtil.getMd5(salt + password);

		PrintWriter out = response.getWriter();

		UserSql sql = new UserSql();

		if (sql.readByUsername(username) == null) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setEmail(email);

			sql.create(user);

			out.print("success");

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			out.print("failed");

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/signUp.jsp");
			dispatcher.forward(request, response);

		}
	}
}
