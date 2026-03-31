package com.tradewisenexus.dto;

public class AddFundsRequest {
    private Long userId;
    private Double amount;

    public AddFundsRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
