package com.tradewisenexus.service;

import com.tradewisenexus.dto.StockResponse;
import com.tradewisenexus.model.Stock;
import com.tradewisenexus.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockResponse> getAllStocks() {
        return stockRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<StockResponse> getTopMovers() {
        return stockRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .sorted(Comparator.comparing(StockResponse::getChangePercent).reversed())
                .limit(5)
                .toList();
    }

    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    private StockResponse mapToResponse(Stock stock) {
        StockResponse response = new StockResponse();
        response.setSymbol(stock.getSymbol());
        response.setCompanyName(stock.getCompanyName());
        response.setCurrentPrice(stock.getCurrentPrice());
        response.setPreviousPrice(stock.getPreviousPrice());
        response.setPreviousClose(stock.getPreviousClose());
        response.setDayHigh(stock.getDayHigh());
        response.setDayLow(stock.getDayLow());

        double base = (stock.getPreviousClose() == null || stock.getPreviousClose() == 0)
                ? stock.getCurrentPrice()
                : stock.getPreviousClose();

        double changePercent = ((stock.getCurrentPrice() - base) / base) * 100.0;
        response.setChangePercent(changePercent);

        return response;
    }
}
