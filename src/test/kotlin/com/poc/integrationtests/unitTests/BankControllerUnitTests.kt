package com.poc.integrationtests.unitTests

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.poc.integrationtests.models.BankAccount
import com.poc.integrationtests.services.BankAccountService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class BankControllerUnitTests(@Autowired val mockMvc: MockMvc, @Autowired private val mapper: ObjectMapper) {

  @MockkBean
  lateinit var bankAccountService: BankAccountService

  val bankAccount = BankAccount(id = 1, accountNumber = "12345", accountHolderName = "Sandro", bankCode = "ING")  // Define the bankAccount variable


  @Test
  fun givenExistingBankAccount_whenGetRequest_thenReturnsBankAccountJsonWithStatus200() {
    every { bankAccountService.getBankAccount(1) } returns bankAccount

    mockMvc.perform(get("/api/bankAccount?id=1"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.bankCode").value("ING"))
  }

  @Test
  fun givenBankAccountDoesntExist_whenGetRequest_thenReturnsStatus400() {
    every { bankAccountService.getBankAccount(2) } returns null;

    mockMvc.perform(get("/api/bankAccount?id=2"))
      .andExpect(status().isBadRequest());
  }


  @Test
  fun whenPostRequestWithBankAccountJson_thenReturnsStatus200() {
    every { bankAccountService.addBankAccount(bankAccount) } returns bankAccount;

    mockMvc.perform(post("/api/bankAccount")
        .content(mapper.writeValueAsString(bankAccount)).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.bankCode").value("ING"));
  }
}