package com.tradewisenexus.controller;

import com.tradewisenexus.dto.StockResponse;
import com.tradewisenexus.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockResponse> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/movers/top")
    public List<StockResponse> getTopMovers() {
        return stockService.getTopMovers();
    }
}
