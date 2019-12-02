package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import beans.Stock;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


@WebServlet("/stock")
public class stockPageServlet extends HttpServlet {

	private String API_KEY = "BAGYRS53D2WXYV9V";

	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String symbol = request.getParameter("symbol");

		PrintWriter out = response.getWriter();

		String stocksInfo = sendGetForStock(symbol);

		List<Stock> stocks = new ArrayList<>();

		if (!stocksInfo.equals("")) {
			stocksInfo = stocksInfo.substring(stocksInfo.indexOf("(Daily)\":") + 9);

			for (int i = 0;  i < 7; i++) {
				Stock stock = new Stock();

				String date = stocksInfo.substring(stocksInfo.indexOf("\"") + 1);
				date = date.substring(0, date.indexOf("\": {"));
				stock.setDate(date);
				System.out.println("date:");
				System.out.println(date);
				JSONObject obj = new JSONObject(stocksInfo);

				String open = obj.getJSONObject(date).getString("1. open");
				out.print(open);
				stock.setOpen(open);

				String high = obj.getJSONObject(date).getString("2. high");
				out.print(high);
				stock.setHigh(high);

				String low = obj.getJSONObject(date).getString("3. low");
				out.print(low);
				stock.setLow(low);

				String close = obj.getJSONObject(date).getString("4. close");
				out.print(close);
				stock.setClose(close);

				String volume = obj.getJSONObject(date).getString("5. volume");
				out.print(volume);
				stock.setVolume(volume);

				stocks.add(stock);

			}
		}

	}

	String sendGetForStock(String symbol) {
		HttpGet req = new HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=BAGYRS53D2WXYV9V");
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
}
