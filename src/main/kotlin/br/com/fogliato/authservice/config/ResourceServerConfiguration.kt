package br.com.fogliato.authservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Configuration
@EnableResourceServer
class ResourceServerConfiguration(
    @Value("\${security.oauth2.client.resource-ids}")
    val resourceId: String
): ResourceServerConfigurerAdapter() {

  override fun configure(resources: ResourceServerSecurityConfigurer) {
    resources.resourceId(resourceId)
  }

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .logout()
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .and().authorizeRequests()
      .anyRequest().fullyAuthenticated()
//      .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .authenticated()
//        .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
//        .antMatchers(HttpMethod.OPTIONS, "/**").access("#oauth2.hasScope('read')")
//        .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
//        .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
//        .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
//        .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
  }
}