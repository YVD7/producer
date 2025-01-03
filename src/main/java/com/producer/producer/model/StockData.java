package com.producer.producer.model;

public class StockData {
    private String ticker;
    private double askPrice;
    private double bidPrice;
    private long volume;

    // Constructors
    public StockData(String ticker, double askPrice, double bidPrice, long volume) {
        this.ticker = ticker;
        this.askPrice = askPrice;
        this.bidPrice = bidPrice;
        this.volume = volume;
    }

    // Getters and Setters
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}
