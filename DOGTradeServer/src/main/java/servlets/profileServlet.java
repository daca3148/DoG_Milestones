package servlets;

import beans.OwnedStock;
import beans.User;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sql.StockSql;
import sql.UserSql;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class profileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("User");

		if (loggedInUser != null) {
			StockSql sql = new StockSql();

			List<OwnedStock> ownedStockList = sql.readOwnedStocks(loggedInUser.getId());

			if (ownedStockList != null) {
				for (int i = 0; i < ownedStockList.size(); i++) {



					stockPageServlet stockPageServlet = new stockPageServlet();
					String stocksInfo = stockPageServlet.sendGetForStock(ownedStockList.get(i).getSymbol());

					String orig1 = "}\n" +
							"    }\n" +
							"}";

					String repl1 = "}\n" +
							"    ]\n" +
							"}";

					String orig2 = "\"Time Series (Daily)\": {";

					String repl2 = "\"Time Series (Daily)\": [";

					stocksInfo = stocksInfo.replace(orig1, repl1);
					stocksInfo = stocksInfo.replace(orig2, repl2);


					System.out.println(stocksInfo);
					System.out.println("StockInfo");

					String arrayOfObjects = stocksInfo.substring(stocksInfo.indexOf("[") + 1, stocksInfo.indexOf("]"));
					String[] arrayOfStringObjects = arrayOfObjects.split("},");


					arrayOfStringObjects[i] = arrayOfStringObjects[i].substring(arrayOfStringObjects[i].indexOf(":") + 1) + "}";

					JSONObject jsonObject = new JSONObject(arrayOfStringObjects[i]);

					String close = jsonObject.getString("4. close");

					double val = Double.parseDouble(close);
					ownedStockList.get(i).setValue(val);
					ownedStockList.get(i).setTotal(ownedStockList.get(i).getQuantity() * val);


				}

				request.setAttribute("ownedStocks", ownedStockList);
			}

			UserSql sql1 = new UserSql();
			User user = sql1.readByUsername(loggedInUser.getUsername());

			session.setAttribute("User", user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/profile.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect("/login");
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String symbol = request.getParameter("search");
		String imgURL = request.getParameter("pic");

		if (symbol != null) {
			symbol = symbol.toUpperCase();
			response.sendRedirect("/stock?symbol=" + symbol);
		} else if (imgURL != null) {

			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("User");

			if (loggedInUser != null) {
				UserSql sql = new UserSql();
				sql.updateImg(imgURL, loggedInUser.getUsername());
			}

			loggedInUser.setImgURL(imgURL);

			session.setAttribute("User", loggedInUser);

			response.sendRedirect("/profile");
		}

	}

}
