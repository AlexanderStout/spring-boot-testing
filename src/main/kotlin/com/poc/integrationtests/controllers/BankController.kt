package com.poc.integrationtests.controllers

import com.poc.integrationtests.models.BankAccount
import com.poc.integrationtests.services.BankAccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bankAccount")
class BankController(var bankAccountService: BankAccountService) {

  @PostMapping
  fun addBankAccount(@RequestBody bankAccount: BankAccount) : ResponseEntity<BankAccount> {
    return ResponseEntity.ok(bankAccountService.addBankAccount(bankAccount))
  }

  @GetMapping
  fun getBankAccount(@RequestParam id:Long) : ResponseEntity<BankAccount> {
    val bankAccount: BankAccount? = bankAccountService.getBankAccount(id);
    return if (bankAccount != null) {
      ResponseEntity(bankAccount, HttpStatus.OK)
    } else {
      ResponseEntity(HttpStatus.BAD_REQUEST)
    }
  }
}