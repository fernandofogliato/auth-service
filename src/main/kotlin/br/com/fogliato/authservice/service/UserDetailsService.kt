package br.com.fogliato.authservice.service

import br.com.fogliato.authservice.model.User
import br.com.fogliato.authservice.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsService(
  val userRepository: UserRepository
): UserDetailsService {

  @Throws(UsernameNotFoundException::class)
  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User $username Not found")

    return org.springframework.security.core.userdetails.User(user.username, user.password, getGrantedAuthorities(user))
  }

  private fun getGrantedAuthorities(user: User?): Collection<GrantedAuthority> {
    val grantedAuthorities: MutableCollection<GrantedAuthority> = ArrayList()
    user?.authorities?.forEach { grantedAuthorities.add(SimpleGrantedAuthority(it.name)) }
    return grantedAuthorities
  }
}