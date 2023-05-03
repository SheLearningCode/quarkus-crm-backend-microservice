/*package com.crm.controller.security

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import javax.enterprise.context.ApplicationScoped

class KeycloakProvider(private val keycloak: Keycloak) {

    @ApplicationScoped
    fun provideRealmResource(){
        val keycloak = KeycloakBuilder.builder()
            .serverUrl(keycloak.serverInfo().toString())
            .realm(keycloak.realm(toString()).toString())
    //(OAuth2Constants.CLIENT_CREDENTIALS);
    }
}*/