package com.poc.integrationtests.models

import jakarta.persistence.*

@Entity
class Person(
  @Column(nullable = false)
  val name: String,
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int?=null,
)