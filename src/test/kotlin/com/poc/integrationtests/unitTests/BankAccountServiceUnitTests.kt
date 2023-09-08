package com.poc.integrationtests.unitTests

import com.poc.integrationtests.models.BankAccount
import com.poc.integrationtests.repos.BankAccountRepository
import com.poc.integrationtests.services.BankAccountService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class BankAccountServiceUnitTests {
  val bankAccountRepository: BankAccountRepository = mockk()
  val bankAccountService = BankAccountService(bankAccountRepository)

  val bankAccount = BankAccount(id = 1, accountNumber = "12345", accountHolderName = "Sandro", bankCode = "QWERQWER")  // Define the bankAccount variable

  @Test
  fun whenGetBankAccount_thenReturnBankAccount() {
    //given
    every { bankAccountRepository.findByIdOrNull(1) } returns bankAccount
    //when
    val result = bankAccountService.getBankAccount(1)
    //then
    verify(exactly = 1) { bankAccountRepository.findByIdOrNull(1) }
    assertEquals(bankAccount, result)
  }
}

