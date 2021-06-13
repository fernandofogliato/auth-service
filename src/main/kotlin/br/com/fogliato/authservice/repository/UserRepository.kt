package br.com.fogliato.authservice.repository

import br.com.fogliato.authservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface UserRepository: JpaRepository<User, String> {

  fun findByUsername(@Param("username") username: String): User?
}