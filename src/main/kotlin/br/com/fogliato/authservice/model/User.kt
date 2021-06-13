package br.com.fogliato.authservice.model

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name="`user`")
class User(
    @Id
    @Column(name = "username", updatable = false, nullable = false)
    val username: String,

    @Size(min = 0, max = 500)
    val password: String,

    @Email
    @Size(min = 0, max = 50)
    val email: String,

    val activated: Boolean,

    @Size(min = 0, max = 100)
    val activationKey: String,

    @Size(min = 0, max = 100)
    val resetPasswordKey: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "username")],
        inverseJoinColumns = [JoinColumn(name = "authority")])
    val authorities: Set<Authority>
)