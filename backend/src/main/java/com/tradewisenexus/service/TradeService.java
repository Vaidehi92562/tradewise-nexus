package com.tradewisenexus.service;

import com.tradewisenexus.dto.TradeRequest;
import com.tradewisenexus.model.Holding;
import com.tradewisenexus.model.Stock;
import com.tradewisenexus.model.Transaction;
import com.tradewisenexus.model.Wallet;
import com.tradewisenexus.repository.HoldingRepository;
import com.tradewisenexus.repository.TransactionRepository;
import com.tradewisenexus.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TradeService {

    private final WalletRepository walletRepository;
    private final HoldingRepository holdingRepository;
    private final TransactionRepository transactionRepository;
    private final StockService stockService;

    public TradeService(WalletRepository walletRepository,
                        HoldingRepository holdingRepository,
                        TransactionRepository transactionRepository,
                        StockService stockService) {
        this.walletRepository = walletRepository;
        this.holdingRepository = holdingRepository;
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
    }

    public String buyStock(TradeRequest request) {
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        Wallet wallet = walletRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        Stock stock = stockService.getStockBySymbol(request.getSymbol());
        double totalCost = stock.getCurrentPrice() * request.getQuantity();

        if (wallet.getBalance() < totalCost) {
            throw new RuntimeException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - totalCost);
        wallet.setUpdatedAt(LocalDateTime.now());
        walletRepository.save(wallet);

        Holding holding = holdingRepository
                .findByUserIdAndStockSymbol(request.getUserId(), stock.getSymbol())
                .orElse(null);

        if (holding == null) {
            holding = new Holding();
            holding.setUserId(request.getUserId());
            holding.setStockSymbol(stock.getSymbol());
            holding.setQuantity(request.getQuantity());
            holding.setAverageBuyPrice(stock.getCurrentPrice());
        } else {
            int oldQty = holding.getQuantity();
            double oldAvg = holding.getAverageBuyPrice();
            int newQty = oldQty + request.getQuantity();
            double newAvg = ((oldQty * oldAvg) + (request.getQuantity() * stock.getCurrentPrice())) / newQty;

            holding.setQuantity(newQty);
            holding.setAverageBuyPrice(newAvg);
        }

        holdingRepository.save(holding);

        Transaction transaction = new Transaction();
        transaction.setUserId(request.getUserId());
        transaction.setStockSymbol(stock.getSymbol());
        transaction.setTransactionType("BUY");
        transaction.setQuantity(request.getQuantity());
        transaction.setPricePerStock(stock.getCurrentPrice());
        transaction.setTotalAmount(totalCost);
        transactionRepository.save(transaction);

        return "Stock bought successfully";
    }

    public String sellStock(TradeRequest request) {
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        Wallet wallet = walletRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        Stock stock = stockService.getStockBySymbol(request.getSymbol());

        Holding holding = holdingRepository
                .findByUserIdAndStockSymbol(request.getUserId(), stock.getSymbol())
                .orElseThrow(() -> new RuntimeException("Holding not found"));

        if (holding.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough quantity to sell");
        }

        double totalValue = stock.getCurrentPrice() * request.getQuantity();

        int remainingQty = holding.getQuantity() - request.getQuantity();
        if (remainingQty == 0) {
            holdingRepository.delete(holding);
        } else {
            holding.setQuantity(remainingQty);
            holdingRepository.save(holding);
        }

        wallet.setBalance(wallet.getBalance() + totalValue);
        wallet.setUpdatedAt(LocalDateTime.now());
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setUserId(request.getUserId());
        transaction.setStockSymbol(stock.getSymbol());
        transaction.setTransactionType("SELL");
        transaction.setQuantity(request.getQuantity());
        transaction.setPricePerStock(stock.getCurrentPrice());
        transaction.setTotalAmount(totalValue);
        transactionRepository.save(transaction);

        return "Stock sold successfully";
    }
}
