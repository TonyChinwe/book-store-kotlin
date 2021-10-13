package com.phisoft.bookstorekotlin.models

import com.phisoft.bookstorekotlin.authentication.Permission
import com.phisoft.bookstorekotlin.authentication.Role
import lombok.Getter
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "ACCOUNT")
class Account() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID", nullable = false)
    private var id: Long? = null

    @Column(name = "IN_ENABLED")
    private var isEnabled: Boolean = false

    @Column(name = "IN_NOT_EXPIRED")
    private var isNotExpired: Boolean = false

    @Column(name = "IN_NOT_LOCKED")
    private var isNotLocked: Boolean = false

    @Column(name = "IN_CREDENTIAL_NOT_EXPIRED")
    private var isCredentialNotExpired: Boolean = false

    @Column(name = "PASSWORD", nullable = false)
    private var password: String? = null

    @Column(name = "USERNAME", unique = true, nullable = false)
    private var username: String? = null

    @ElementCollection(targetClass = Permission::class)
    @Enumerated(EnumType.STRING)
    @Column(name = "PERMISSION")
    private var permissions: List<Permission> = ArrayList()

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private var role: Role? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun isEnabled(): Boolean {
        return isEnabled
    }

    fun setEnabled(enabled: Boolean) {
        isEnabled = enabled
    }

    fun isNotExpired(): Boolean {
        return isNotExpired
    }

    fun setNotExpired(notExpired: Boolean) {
        isNotExpired = notExpired
    }

    fun isNotLocked(): Boolean {
        return isNotLocked
    }

    fun setNotLocked(notLocked: Boolean) {
        isNotLocked = notLocked
    }

    fun isCredentialNotExpired(): Boolean {
        return isCredentialNotExpired
    }

    fun setCredentialNotExpired(credentialNotExpired: Boolean) {
        isCredentialNotExpired = credentialNotExpired
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getPermissions(): List<Permission> {
        return permissions
    }

    fun setPermissions(permissions: List<Permission>) {
        this.permissions = permissions
    }

    fun getRole(): Role? {
        return role
    }

    fun setRole(role: Role) {
        this.role = role
    }

}