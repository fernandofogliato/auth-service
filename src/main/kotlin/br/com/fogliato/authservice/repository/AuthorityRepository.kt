package br.com.fogliato.authservice.repository

import br.com.fogliato.authservice.model.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository: JpaRepository<Authority, String> {
  fun findByName(name: String): Authority
}