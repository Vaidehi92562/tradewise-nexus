package com.tradewisenexus.service;

import com.tradewisenexus.dto.AddFundsRequest;
import com.tradewisenexus.model.Transaction;
import com.tradewisenexus.model.Wallet;
import com.tradewisenexus.repository.TransactionRepository;
import com.tradewisenexus.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public Wallet addFunds(AddFundsRequest request) {
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Wallet wallet = walletRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance() + request.getAmount());
        wallet.setUpdatedAt(LocalDateTime.now());
        Wallet savedWallet = walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setUserId(request.getUserId());
        transaction.setStockSymbol("FUNDS");
        transaction.setTransactionType("FUND_ADD");
        transaction.setQuantity(1);
        transaction.setPricePerStock(request.getAmount());
        transaction.setTotalAmount(request.getAmount());
        transactionRepository.save(transaction);

        return savedWallet;
    }
}
