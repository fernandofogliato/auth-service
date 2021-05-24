package br.com.fogliato.authservice.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/")
class AuthController {

  @RequestMapping("/user")
  fun getCurrentLoggedInUser(user: Principal?): Principal? {
    return user
  }
}