package stockinfoapp.stockinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;



public class StockTickerDAO {
	private static final StockTickerDAO stockDAO = new StockTickerDAO();
	private static HashMap<String, StockBean> stocks = new HashMap<String, StockBean>();
	private static HashMap<String, StockInfo> stocksdb = new HashMap<String, StockInfo>();

	private static final long TWENTY_MIN = 1200000;

	private StockTickerDAO() {
	}

	public static StockTickerDAO getInstance() {
		return stockDAO;
	}

	/**
	 * 
	 * @param symbol
	 * @return StockBean will return null if unable to retrieve information
	 */
	public StockBean getStockPrice(String symbol) {
		StockBean stock;
		long currentTime = (new Date()).getTime();
		// Check last updated and only pull stock on average every 20 minutes
		if (stocks.containsKey(symbol)) {
			stock = stocks.get(symbol);
			if (currentTime - stock.getLastUpdated() > TWENTY_MIN) {
				stock = refreshStockInfo(symbol, currentTime);
			}
		} else {
			stock = refreshStockInfo(symbol, currentTime);
		}
		return stock;
	}

	public StockBean getStockPrice(String[] symbol) {
		StockBean stock = null;
		long currentTime = (new Date()).getTime();
		// Check last updated and only pull stock on average every 20 minutes

		for (String s : symbol) {
			System.out.println("SYMBOL : " + s);
			if (stocks.containsKey(symbol)) {
				stock = stocks.get(symbol);
				if (currentTime - stock.getLastUpdated() > TWENTY_MIN) {
					stock = refreshStockInfo(s, currentTime);

				}
			} else {
				stock = refreshStockInfo(s, currentTime);

			}
		}

		return stock;
	}

	public StockInfo getStockPriceDB(String[] symbol) {
		StockInfo stock = null;
		long currentTime = (new Date()).getTime();
		// Check last updated and only pull stock on average every 20 minutes

		for (String s : symbol) {
			System.out.println("SYMBOL : " + s);
			if (stocks.containsKey(symbol)) {
				stock = stocksdb.get(symbol);
				if (currentTime - stock.getLastUpdated() > TWENTY_MIN) {
					stock = refreshStockInfoDB(s, currentTime);

				}
			} else {
				stock = refreshStockInfoDB(s, currentTime);

			}
		}

		return stock;
	}

	
	public StockInfo getStockPriceDB(String symbol) {
		StockInfo stock = null;
		long currentTime = (new Date()).getTime();
		// Check last updated and only pull stock on average every 20 minutes

		
				stock = refreshStockInfoDB(symbol, currentTime);

			
		

		return stock;
	}
	
	// This is synched so we only do one request at a time
	// If yahoo doesn't return stock info we will try to return it from the map
	// in memory
	private synchronized StockBean refreshStockInfo(String symbol, long time) {
		try {
			URL yahoofin = new URL("http://finance.yahoo.com/d/quotes.csv?s="
					+ symbol + "&f=sl1d1t1c1ohgc8&e=.csv");
			URLConnection yc = yahoofin.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println("here it is : " + inputLine);
				String[] yahooStockInfo = inputLine.split(",");
				StockBean stockInfo = new StockBean();
				stockInfo.setTicker(yahooStockInfo[0].replaceAll("\"", ""));
				stockInfo.setPrice(Float.valueOf(yahooStockInfo[1]));
				stockInfo.setChange(Float.valueOf(yahooStockInfo[4]));
				if ("N/A - N/A".equals(yahooStockInfo[5])) {
					stockInfo.setOpenPrice(0);
				} else {
					stockInfo.setOpenPrice(Float.valueOf(yahooStockInfo[5]));

				}
				stockInfo.setTodaysHigh(Float.valueOf(yahooStockInfo[6]));
				stockInfo.setTodaysLow(Float.valueOf(yahooStockInfo[7]));
				
				if (yahooStockInfo[8].contains("-") || "N/A".equals(yahooStockInfo[8])) {
					stockInfo.setAfterhours(0);
				}else{
					stockInfo.setAfterhours(Float.valueOf(yahooStockInfo[8]));

				}
				stockInfo
						.setChartUrlSmall("http://ichart.finance.yahoo.com/t?s="
								+ stockInfo.getTicker());
				stockInfo
						.setChartUrlLarge("http://chart.finance.yahoo.com/w?s="
								+ stockInfo.getTicker());
				stockInfo.setLastUpdated(time);
				stockInfo.setEarningsDate(getEarnigsInfo(symbol));

				stocks.put(symbol, stockInfo);
				break;
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stocks.get(symbol);
	}

	private synchronized StockInfo refreshStockInfoDB(String symbol, long time) {
		try {
			URL yahoofin = new URL("http://finance.yahoo.com/d/quotes.csv?s="
					+ symbol + "&f=sl1d1t1c1ohgc8&e=.csv");
			URLConnection yc = yahoofin.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println("here it is : " + inputLine);
				String[] yahooStockInfo = inputLine.split(",");
				StockInfo stockInfo = new StockInfo();
				stockInfo.setTicker(yahooStockInfo[0].replaceAll("\"", ""));
				stockInfo.setPrice(Float.valueOf(yahooStockInfo[1]));
				stockInfo.setStockchange(Float.valueOf(yahooStockInfo[4]));
				if ("N/A - N/A".equals(yahooStockInfo[5])) {
					stockInfo.setOpenPrice(0);
				} else {
					stockInfo.setOpenPrice(Float.valueOf(yahooStockInfo[5]));

				}
				//stockInfo.setOpenPrice(Float.valueOf(yahooStockInfo[5]));
				stockInfo.setTodaysHigh(Float.valueOf(yahooStockInfo[6]));
				stockInfo.setTodaysLow(Float.valueOf(yahooStockInfo[7]));
				
				if (yahooStockInfo[8].contains("-") || "N/A".equals(yahooStockInfo[8])) {
					stockInfo.setAfterhours(0);
				}else{
					stockInfo.setAfterhours(Float.valueOf(yahooStockInfo[8]));

				}				stockInfo
						.setChartUrlSmall("http://ichart.finance.yahoo.com/t?s="
								+ stockInfo.getTicker());
				stockInfo
						.setChartUrlLarge("http://chart.finance.yahoo.com/w?s="
								+ stockInfo.getTicker());
				stockInfo.setLastUpdated(time);
				stockInfo.setEarningsDate(getEarnigsInfo(symbol));
				stocksdb.put(symbol, stockInfo);
				break;
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stocksdb.get(symbol);
	}
	
	
	private String getEarnigsInfo(String stock){
		
        String earningsDetails = null;


        try {

            Document doc = Jsoup.connect("http://finviz.com/quote.ashx?t="+stock).get();
            Elements table = doc.select("body td");
            System.out.println(table.text());
            for (Element td : table) {
                if (td.text().contains("Earnings")) {
					// System.out.println("*************************************************");
					// System.out.println(td.getAllElements().get(218));
					// System.out.println(td.getAllElements().get(219));
					// System.out.println(td.getAllElements().get(220));
					// System.out.println(td.getAllElements().get(221));
					// System.out.println(td.getAllElements().get(222));
					// System.out.println(td.getAllElements().get(223));
					// System.out.println(td.getAllElements().get(224));
					// System.out.println(td.getAllElements().get(225));
					// System.out.println(td.getAllElements().get(226));
					// System.out.println("*************************************************");
					//
                    earningsDetails = td.getAllElements().get(218).text()+td.getAllElements().get(219).text()+td.getAllElements().get(220).text()+td.getAllElements().get(221).text()+td.getAllElements().get(222).text()+td.getAllElements().get(223).text()+td.getAllElements().get(224).text()+td.getAllElements().get(225).text()+td.getAllElements().get(226).text();
                    //System.out.print(td.text().substring(900, 1020));
                    System.out.println("Printing : : "+earningsDetails);
                    break;
                }

            }

        } catch (IOException ex) {
        	
        	ex.printStackTrace();

        }

    
		
		return earningsDetails;
		
	}
}
