package com.tradewisenexus.service;

import com.tradewisenexus.dto.WatchlistResponse;
import com.tradewisenexus.model.Stock;
import com.tradewisenexus.model.User;
import com.tradewisenexus.model.Watchlist;
import com.tradewisenexus.repository.StockRepository;
import com.tradewisenexus.repository.UserRepository;
import com.tradewisenexus.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    public WatchlistService(WatchlistRepository watchlistRepository,
                            UserRepository userRepository,
                            StockRepository stockRepository) {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    public Watchlist addToWatchlist(Long userId, String symbol) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String normalizedSymbol = symbol == null ? "" : symbol.trim().toUpperCase();

        Stock stock = stockRepository.findBySymbol(normalizedSymbol)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        boolean alreadyExists = watchlistRepository.findByUserId(userId).stream()
                .anyMatch(item -> item.getStockSymbol() != null
                        && item.getStockSymbol().equalsIgnoreCase(normalizedSymbol));

        if (alreadyExists) {
            throw new RuntimeException("Stock already in watchlist");
        }

        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setStockSymbol(stock.getSymbol());

        return watchlistRepository.save(watchlist);
    }

    public List<WatchlistResponse> getWatchlistByUserId(Long userId) {
        return watchlistRepository.findByUserId(userId).stream().map(item -> {
            Stock stock = stockRepository.findBySymbol(item.getStockSymbol()).orElse(null);
            return new WatchlistResponse(
                    item.getId(),
                    item.getStockSymbol(),
                    stock != null ? stock.getCompanyName() : item.getStockSymbol(),
                    stock != null ? stock.getCurrentPrice() : 0.0
            );
        }).toList();
    }

    public void removeFromWatchlist(Long userId, String symbol) {
        String normalizedSymbol = symbol == null ? "" : symbol.trim().toUpperCase();

        Watchlist watchlist = watchlistRepository.findByUserId(userId).stream()
                .filter(item -> item.getStockSymbol() != null
                        && item.getStockSymbol().equalsIgnoreCase(normalizedSymbol))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stock not found in watchlist"));

        watchlistRepository.delete(watchlist);
    }
}
