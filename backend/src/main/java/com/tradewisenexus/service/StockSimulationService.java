package com.tradewisenexus.service;

import com.tradewisenexus.model.Stock;
import com.tradewisenexus.repository.StockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class StockSimulationService {

    private final StockRepository stockRepository;
    private final Random random = new Random();

    public StockSimulationService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void simulatePrices() {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {
            double current = stock.getCurrentPrice();
            double changePercent = (random.nextDouble() * 2.0) - 1.0;
            double newPrice = current + (current * changePercent / 100.0);

            if (newPrice < 1) {
                newPrice = current;
            }

            stock.setPreviousPrice(current);
            stock.setCurrentPrice(round(newPrice));

            if (stock.getDayHigh() == null || newPrice > stock.getDayHigh()) {
                stock.setDayHigh(round(newPrice));
            }

            if (stock.getDayLow() == null || newPrice < stock.getDayLow()) {
                stock.setDayLow(round(newPrice));
            }

            stock.setUpdatedAt(LocalDateTime.now());
        }

        stockRepository.saveAll(stocks);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
