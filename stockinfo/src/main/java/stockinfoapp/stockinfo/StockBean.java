package stockinfoapp.stockinfo;

public class StockBean {
    String ticker;
    float price;
    float change;
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
    public float getChange() {
        return change;
    }
    public void setChange(float change) {
        this.change = change;
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

