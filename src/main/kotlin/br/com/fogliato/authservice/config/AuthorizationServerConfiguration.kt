package br.com.fogliato.authservice.config

import br.com.fogliato.authservice.domain.Authorities
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
    @Qualifier("authenticationManagerBean")
    val authenticationManager: AuthenticationManager,

    val passwordEncoder: PasswordEncoder,

    val dataSource: DataSource,

    @Value("\${security.oauth2.client.client-id}")
    val clientId: String,

    @Value("\${security.oauth2.client.authorized-grant-types}")
    val authorizedGrantTypes: Array<String>,

    @Value("\${security.oauth2.client.resource-ids}")
    val resourceIds: String,

    @Value("\${security.oauth2.client.scope}")
    val scopes: Array<String>,

    @Value("\${security.oauth2.client.client-secret}")
    val secret: String,

    @Value("\${security.oauth2.client.access-token-validity-seconds}")
    val accessTokenValiditySeconds: Int

): AuthorizationServerConfigurerAdapter() {

    @Bean
    fun tokenStore(): JdbcTokenStore {
        return JdbcTokenStore(dataSource)
    }

    @Throws(java.lang.Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.passwordEncoder(passwordEncoder)
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore())
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource)
            .withClient(clientId)
            .authorizedGrantTypes(*authorizedGrantTypes)
            .authorities(Authorities.values().toString())
            .resourceIds(resourceIds)
            .scopes(*scopes)
            .secret(secret)
            .accessTokenValiditySeconds(accessTokenValiditySeconds)
    }
}