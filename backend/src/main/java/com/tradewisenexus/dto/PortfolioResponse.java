package com.tradewisenexus.dto;

import java.util.List;

public class PortfolioResponse {

    public static class HoldingItem {
        private String stockSymbol;
        private Integer quantity;
        private Double averageBuyPrice;
        private Double currentPrice;
        private Double investedValue;
        private Double currentValue;
        private Double profitLoss;

        public HoldingItem() {
        }

        public String getStockSymbol() {
            return stockSymbol;
        }

        public void setStockSymbol(String stockSymbol) {
            this.stockSymbol = stockSymbol;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getAverageBuyPrice() {
            return averageBuyPrice;
        }

        public void setAverageBuyPrice(Double averageBuyPrice) {
            this.averageBuyPrice = averageBuyPrice;
        }

        public Double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(Double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public Double getInvestedValue() {
            return investedValue;
        }

        public void setInvestedValue(Double investedValue) {
            this.investedValue = investedValue;
        }

        public Double getCurrentValue() {
            return currentValue;
        }

        public void setCurrentValue(Double currentValue) {
            this.currentValue = currentValue;
        }

        public Double getProfitLoss() {
            return profitLoss;
        }

        public void setProfitLoss(Double profitLoss) {
            this.profitLoss = profitLoss;
        }
    }

    private Double totalInvested;
    private Double currentValue;
    private Double totalProfitLoss;
    private List<HoldingItem> holdings;

    public PortfolioResponse() {
    }

    public Double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(Double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getTotalProfitLoss() {
        return totalProfitLoss;
    }

    public void setTotalProfitLoss(Double totalProfitLoss) {
        this.totalProfitLoss = totalProfitLoss;
    }

    public List<HoldingItem> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<HoldingItem> holdings) {
        this.holdings = holdings;
    }
}
