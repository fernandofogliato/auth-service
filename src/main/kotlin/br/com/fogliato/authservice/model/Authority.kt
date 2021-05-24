package br.com.fogliato.authservice.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
class Authority(
  @Id
  @NotNull
  @Size(min = 0, max = 50)
  val name: String
)