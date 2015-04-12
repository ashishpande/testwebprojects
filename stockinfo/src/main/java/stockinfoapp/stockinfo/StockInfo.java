package stockinfoapp.stockinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCKINFO")
public class StockInfo {
	//private long stockinfoId;
	private long id;

	String ticker;
	float price;
	float stockchange;

	String chartUrlSmall;
	String chartUrlLarge;
	long lastUpdated;
	float todaysHigh;
	float todaysLow;
	float openPrice;
	float afterhours;
	String earningsDate;

	public String getEarningsDate() {
		return earningsDate;
	}

	public void setEarningsDate(String earningsDate) {
		this.earningsDate = earningsDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
//		public long getStockinfoId() {
//		return stockinfoId;
//	}
//
//	public void setStockinfoId(long stockinfoId) {
//		this.stockinfoId = stockinfoId;
//	}

	public float getAfterhours() {
		return afterhours;
	}

	public void setAfterhours(float afterhours) {
		this.afterhours = afterhours;
	}

	public float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}

	public float getTodaysLow() {
		return todaysLow;
	}

	public void setTodaysLow(float todaysLow) {
		this.todaysLow = todaysLow;
	}

	public float getStockchange() {
		return stockchange;
	}

	public void setStockchange(float stockchange) {
		this.stockchange = stockchange;
	}

	public float getTodaysHigh() {
		return todaysHigh;
	}

	public void setTodaysHigh(float todaysHigh) {
		this.todaysHigh = todaysHigh;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getChartUrlSmall() {
		return chartUrlSmall;
	}

	public void setChartUrlSmall(String chartUrlSmall) {
		this.chartUrlSmall = chartUrlSmall;
	}

	public String getChartUrlLarge() {
		return chartUrlLarge;
	}

	public void setChartUrlLarge(String chartUrlLarge) {
		this.chartUrlLarge = chartUrlLarge;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
