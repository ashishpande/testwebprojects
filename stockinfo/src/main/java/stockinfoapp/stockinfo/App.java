package stockinfoapp.stockinfo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		StockBean stock = new StockBean();
		StockInfo infoStock = new StockInfo();
		List<StockBean> stocksList = new ArrayList<StockBean>();
		List<StockInfo> stockInfoList = new ArrayList<StockInfo>();
		for (String stocks : args) {
			System.out.println("Symbol : " + stocks);
			stock = StockTickerDAO.getInstance().getStockPrice(stocks);
			infoStock = StockTickerDAO.getInstance().getStockPriceDB(stocks);
			System.out.println("Open Price : " + stock.getOpenPrice());
			System.out.println("Todays High : " + stock.getTodaysHigh());
			System.out.println("Todays Low : " + stock.getTodaysLow());
			System.out.println("Last Price : " + stock.getPrice());
			System.out.println("Aftere hours : " + stock.getAfterhours());
			stocksList.add(stock);
			stockInfoList.add(infoStock);

		}
		generateCsv(stocksList);
	}

	public static void generateCsv(List<StockBean> list) {

		try {

			FileWriter writer = new FileWriter("stockinfo.csv");

			writer.append(",");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();

				writer.append(stock.getTicker() + ',');

			}

			writer.append('\n');

			writer.append("HIGH,");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();

				writer.append(Float.valueOf(stock.getTodaysHigh()).toString() + ',');
			}

			writer.append('\n');

			writer.append("LOW,");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();

				writer.append(Float.valueOf(stock.getTodaysLow()).toString() + ',');
			}

			writer.append('\n');

			writer.append("CLOSING,");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();

				writer.append(Float.valueOf(stock.getPrice()).toString() + ',');
			}

			writer.append('\n');

			writer.append("AFTER HOURS,");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();

				writer.append(Float.valueOf(stock.getAfterhours()).toString() + ',');
			}
			writer.append('\n');

			writer.append("OPEN PRICE,");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();

				writer.append(Float.valueOf(stock.getOpenPrice()).toString() + ',');
			}

			writer.append('\n');
			writer.append("EARNINGS INFO,");

			for (Iterator<StockBean> stockListIterator = list.iterator(); stockListIterator
					.hasNext();) {
				StockBean stock = stockListIterator.next();
				writer.append(stock.getEarningsDate()+ ',');
			}

			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}