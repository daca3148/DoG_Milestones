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

@WebServlet("/home")
public class homeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

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

			Long id = sql.create(user);

			out.print("success");

			user.setId(id);

			HttpSession session = request.getSession();
			session.setAttribute("User", user);

			response.sendRedirect("/profile");
		} else {
			out.print("failed");

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/signUp.jsp");
			dispatcher.forward(request, response);

		}
	}
}