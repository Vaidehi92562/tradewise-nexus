package com.tradewisenexus.service;

import com.tradewisenexus.dto.PortfolioResponse;
import com.tradewisenexus.model.Holding;
import com.tradewisenexus.model.Stock;
import com.tradewisenexus.repository.HoldingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    private final HoldingRepository holdingRepository;
    private final StockService stockService;

    public PortfolioService(HoldingRepository holdingRepository, StockService stockService) {
        this.holdingRepository = holdingRepository;
        this.stockService = stockService;
    }

    public PortfolioResponse getPortfolio(Long userId) {
        List<Holding> holdings = holdingRepository.findByUserId(userId);

        List<PortfolioResponse.HoldingItem> items = new ArrayList<>();
        double totalInvested = 0.0;
        double currentValue = 0.0;

        for (Holding holding : holdings) {
            Stock stock = stockService.getStockBySymbol(holding.getStockSymbol());

            double invested = holding.getAverageBuyPrice() * holding.getQuantity();
            double current = stock.getCurrentPrice() * holding.getQuantity();
            double pnl = current - invested;

            PortfolioResponse.HoldingItem item = new PortfolioResponse.HoldingItem();
            item.setStockSymbol(holding.getStockSymbol());
            item.setQuantity(holding.getQuantity());
            item.setAverageBuyPrice(holding.getAverageBuyPrice());
            item.setCurrentPrice(stock.getCurrentPrice());
            item.setInvestedValue(invested);
            item.setCurrentValue(current);
            item.setProfitLoss(pnl);

            items.add(item);

            totalInvested += invested;
            currentValue += current;
        }

        PortfolioResponse response = new PortfolioResponse();
        response.setTotalInvested(totalInvested);
        response.setCurrentValue(currentValue);
        response.setTotalProfitLoss(currentValue - totalInvested);
        response.setHoldings(items);

        return response;
    }
}
