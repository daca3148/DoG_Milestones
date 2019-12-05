package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import beans.OwnedStock;
import beans.Stock;
import beans.User;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import sql.StockSql;
import sql.UserSql;

import java.util.Random;


@WebServlet("/stock")
public class stockPageServlet extends HttpServlet {

	private String API_KEY = "MMK0V9IERANREGLC";

	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	private String[] otherStocks = {"T", "ADBE", "PYPL", "INTC", "AAPL", "NVDA", "MSFT", "V", "ETSY", "F"};

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String symbol = request.getParameter("symbol");

		PrintWriter out = response.getWriter();

		String stocksInfo = sendGetForStock(symbol);

		//out.print(stocksInfo);

		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("User");

		if (loggedInUser == null) {

			response.sendRedirect("/login");

		} else if (!stocksInfo.contains("Error Message")) {
			List<Stock> stocks = getStockListFromJSON(stocksInfo, symbol);

			List<String> otherFiveStockSymbols = new ArrayList<>();

			List<Stock> otherFiveStocks = new ArrayList<>();

			for (int i = 0; i < 2; i++) {
				Boolean isValid = false;
				String otherStockSymbol = "";

				while (!isValid) {
					Random random = new Random();
					otherStockSymbol = otherStocks[random.nextInt(10)];
					if (!otherStockSymbol.equals(symbol) && !otherFiveStockSymbols.contains(otherStockSymbol)) {
						isValid = true;
					}
				}

				otherFiveStockSymbols.add(otherStockSymbol);

				String stockJSON = sendGetForStock(otherStockSymbol);

				Stock stock = getStockListFromJSON(stockJSON, otherStockSymbol).get(0);
				otherFiveStocks.add(stock);

			}

			StockSql sql = new StockSql();
			OwnedStock stock = sql.readStock(symbol, loggedInUser.getId());

			if (stock != null) {
				request.setAttribute("quantity", stock.getQuantity());
			} else {
				request.setAttribute("quantity", 0);
			}

			request.setAttribute("symbol", symbol);
			request.setAttribute("stockDays", stocks);
			request.setAttribute("otherStocks", otherFiveStocks);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/StockPage.jsp");
			dispatcher.forward(request, response);
		} else {

			List<String> otherFiveStockSymbols = new ArrayList<>();

			List<Stock> otherFiveStocks = new ArrayList<>();

			for (int i = 0; i < 2; i++) {
				Boolean isValid = false;
				String otherStockSymbol = "";

				while (!isValid) {
					Random random = new Random();
					otherStockSymbol = otherStocks[random.nextInt(10)];
					if (!otherStockSymbol.equals(symbol) && !otherFiveStockSymbols.contains(otherStockSymbol)) {
						isValid = true;
					}
				}

				otherFiveStockSymbols.add(otherStockSymbol);

				String stockJSON = sendGetForStock(otherStockSymbol);

				Stock stock = getStockListFromJSON(stockJSON, otherStockSymbol).get(0);
				otherFiveStocks.add(stock);

				request.setAttribute("otherStocks", otherFiveStocks);

			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/StockErrorPage.jsp");
			dispatcher.forward(request, response);
		}
	}

	List<Stock> getStockListFromJSON(String stocksInfo, String symbol) {
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
		//out.print(stocksInfo);

		List<Stock> stocks = new ArrayList<>();

		int dateIndex = stocksInfo.indexOf("\"") + 1;

		if (!stocksInfo.equals("")) {
			//ßßßstocksInfo = stocksInfo.substring(stocksInfo.indexOf("(Daily)\":") + 9);

			//JSONObject jsonObject = new JSONObject(stocksInfo);

			//JSONArray jsonArray = jsonObject.getJSONArray("Time Series (Daily)");

			String name = "";

			switch (symbol) {
				case "T":
					name = "AT&T";
					break;

				case "ADBE":
					name = "Adobe";
					break;

				case "PYPL":
					name = "PayPal";
					break;

				case "INTC":
					name = "Intel";
					break;

				case "AAPL":
					name = "Apple";
					break;

				case "NVDA":
					name = "Nvidia";
					break;

				case "MSFT":
					name = "Microsoft";
					break;

				case "V":
					name = "Visa";
					break;

				case "ETSY":
					name = "Etsy";
					break;

				case "F":
					name = "Ford";
					break;

				default:
					break;

			}

			for (int i = 0; i < 7; i++) {
				Stock stock = new Stock();

				arrayOfStringObjects[i] = arrayOfStringObjects[i].substring(arrayOfStringObjects[i].indexOf(":") + 1) + "}";

				System.out.println("Testerd");

				System.out.println(arrayOfStringObjects[i]);

				JSONObject jsonObject = new JSONObject(arrayOfStringObjects[i]);

				stock.setSymbol(symbol);

				stock.setName(name);

				String open = jsonObject.getString("1. open");
				System.out.print(open);
				stock.setOpen(open);

				String high = jsonObject.getString("2. high");
				//out.print(high);
				stock.setHigh(high);

				String low = jsonObject.getString("3. low");
				//out.print(low);
				stock.setLow(low);

				String close = jsonObject.getString("4. close");
				//out.print(close);
				stock.setClose(close);

				String volume = jsonObject.getString("5. volume");
				//out.print(volume);
				stock.setVolume(volume);

				stocks.add(stock);

			}

			return stocks;
		} else {
			return null;
		}

	}


	String sendGetForStock(String symbol) {
		HttpGet req = new HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=MMK0V9IERANREGLC");
		try (CloseableHttpResponse resp = httpClient.execute(req)) {
			HttpEntity entity = resp.getEntity();
			Header headers = entity.getContentType();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String symbol = request.getParameter("search");
		String numberBuy = request.getParameter("numberBuy");
		String numberSell = request.getParameter("numberSell");
		String sym = request.getParameter("sym");
		String priceStr = request.getParameter("price");

		if (symbol != null){
			symbol = symbol.toUpperCase();
			response.sendRedirect("/stock?symbol=" + symbol);
		}

		if (numberBuy != null) {

			double price = Double.parseDouble(priceStr);

			int num = Integer.parseInt(numberBuy);

			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("User");

			UserSql sql = new UserSql();
			User user = sql.readByUsername(loggedInUser.getUsername());

			double cost = price * num;

			sql.updateMoney(user.getMoney() - cost, loggedInUser.getUsername());

			StockSql sql1 = new StockSql();

			OwnedStock ownedStock = sql1.readStock(sym, loggedInUser.getId());

			if (ownedStock == null) {
				OwnedStock stock = new OwnedStock();
				stock.setQuantity(num);
				stock.setSymbol(sym);
				stock.setUserId(loggedInUser.getId());

				sql1.create(stock);
			} else {
				int quantity = ownedStock.getQuantity() + num;
				sql1.updateQuantity(sym, loggedInUser.getId(), quantity);
			}

			response.sendRedirect("/profile");
		}

		if (numberSell != null) {

			double price = Double.parseDouble(priceStr);

			int num = Integer.parseInt(numberSell);

			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("User");

			UserSql sql = new UserSql();
			User user = sql.readByUsername(loggedInUser.getUsername());

			double cost = price * num;

			sql.updateMoney(user.getMoney() + cost, loggedInUser.getUsername());

			StockSql sql1 = new StockSql();

			OwnedStock ownedStock = sql1.readStock(sym, loggedInUser.getId());

			int quantity = ownedStock.getQuantity() - num;
			sql1.updateQuantity(sym, loggedInUser.getId(), quantity);

			response.sendRedirect("/profile");

		}
	}
}
