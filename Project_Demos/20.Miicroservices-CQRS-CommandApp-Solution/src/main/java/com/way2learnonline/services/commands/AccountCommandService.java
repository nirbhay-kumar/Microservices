package com.way2learnonline.services.commands;

import java.util.concurrent.CompletableFuture;

import com.way2learnonline.dto.commands.AccountCreateDTO;
import com.way2learnonline.dto.commands.MoneyCreditDTO;
import com.way2learnonline.dto.commands.MoneyDebitDTO;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
