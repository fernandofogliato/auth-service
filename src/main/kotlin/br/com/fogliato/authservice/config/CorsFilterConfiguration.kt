package br.com.fogliato.authservice.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsFilterConfiguration {

  @Bean
  fun corsFilter(): FilterRegistrationBean<CorsFilter> {
    val config = CorsConfiguration()
    config.allowCredentials = true
    config.addAllowedOrigin("*")
    config.addAllowedHeader("*")
    config.addAllowedMethod("*")

    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", config)

    val bean = FilterRegistrationBean(CorsFilter(source))
    bean.order = 0
    return bean
  }
}