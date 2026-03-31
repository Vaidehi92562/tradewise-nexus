package com.tradewisenexus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String symbol;

    private String companyName;
    private Double currentPrice;
    private Double previousPrice;
    private Double previousClose;
    private Double dayHigh;
    private Double dayLow;
    private LocalDateTime updatedAt;

    public Stock() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(Double currentPrice) { this.currentPrice = currentPrice; }

    public Double getPreviousPrice() { return previousPrice; }
    public void setPreviousPrice(Double previousPrice) { this.previousPrice = previousPrice; }

    public Double getPreviousClose() { return previousClose; }
    public void setPreviousClose(Double previousClose) { this.previousClose = previousClose; }

    public Double getDayHigh() { return dayHigh; }
    public void setDayHigh(Double dayHigh) { this.dayHigh = dayHigh; }

    public Double getDayLow() { return dayLow; }
    public void setDayLow(Double dayLow) { this.dayLow = dayLow; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
