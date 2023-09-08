package com.poc.integrationtests.repos

import com.poc.integrationtests.models.BankAccount
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BankAccountRepository : CrudRepository<BankAccount, Long>