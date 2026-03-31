package com.tradewisenexus.service;

import com.tradewisenexus.dto.TransactionResponse;
import com.tradewisenexus.model.Transaction;
import com.tradewisenexus.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionResponse> getTransactionsByUserId(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserIdOrderByTimestampDesc(userId);

        return transactions.stream().map(transaction -> {
            TransactionResponse response = new TransactionResponse();
            response.setId(transaction.getId());
            response.setStockSymbol(transaction.getStockSymbol());
            response.setTransactionType(transaction.getTransactionType());
            response.setQuantity(transaction.getQuantity());
            response.setPricePerStock(transaction.getPricePerStock());
            response.setTotalAmount(transaction.getTotalAmount());
            response.setTimestamp(transaction.getTimestamp());
            return response;
        }).toList();
    }
}
