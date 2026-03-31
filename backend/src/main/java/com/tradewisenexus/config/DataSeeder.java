package com.tradewisenexus.config;

import com.tradewisenexus.model.Stock;
import com.tradewisenexus.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder {

    private final StockRepository stockRepository;

    public DataSeeder(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @PostConstruct
    public void seedStocks() {
        if (stockRepository.count() > 0) {
            return;
        }

        List<Stock> stocks = List.of(
                createStock("RELIANCE", "Reliance Industries", 2945.00),
                createStock("TCS", "Tata Consultancy Services", 4120.00),
                createStock("INFY", "Infosys", 1645.00),
                createStock("HDFCBANK", "HDFC Bank", 1718.00),
                createStock("ICICIBANK", "ICICI Bank", 1239.00),
                createStock("SBIN", "State Bank of India", 812.00),
                createStock("LT", "Larsen & Toubro", 3688.00),
                createStock("WIPRO", "Wipro", 514.00),
                createStock("HCLTECH", "HCL Technologies", 1672.00),
                createStock("AXISBANK", "Axis Bank", 1184.00)
        );

        stockRepository.saveAll(stocks);
    }

    private Stock createStock(String symbol, String companyName, double price) {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setCompanyName(companyName);
        stock.setCurrentPrice(price);
        stock.setPreviousPrice(price);
        stock.setPreviousClose(price);
        stock.setDayHigh(price);
        stock.setDayLow(price);
        return stock;
    }
}
