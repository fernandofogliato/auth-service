package br.com.fogliato.authservice.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Authority(
  @Id
  val name: String
)