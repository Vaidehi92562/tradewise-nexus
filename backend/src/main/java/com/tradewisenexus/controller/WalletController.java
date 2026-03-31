package com.tradewisenexus.controller;

import com.tradewisenexus.dto.AddFundsRequest;
import com.tradewisenexus.model.Wallet;
import com.tradewisenexus.service.WalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@CrossOrigin(origins = "*")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public Wallet getWallet(@PathVariable Long userId) {
        return walletService.getWalletByUserId(userId);
    }

    @PostMapping("/add-funds")
    public Wallet addFunds(@RequestBody AddFundsRequest request) {
        return walletService.addFunds(request);
    }
}
