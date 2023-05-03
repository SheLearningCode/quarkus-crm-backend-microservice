package com.crm.controller.security
import com.crm.controller.Routes.ENTRY_PATH
import com.crm.model.Role.ADMIN
import com.crm.model.Role.STUDENT
import com.crm.model.Role.TEACHER
import io.quarkus.security.ForbiddenException
import io.quarkus.security.identity.SecurityIdentity
import org.keycloak.representations.JsonWebToken
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/** TODO: check which authorization policies implementation is better for the authentication with keycloak:
        -keycloak-quarkus-adapter(deprecated!) or
        -keycloak server with
        JWT-Token approach(best practice in real world projects) or
        -'security identity'/quarkus security approach'
        -as @rolesAllowed-check is actually business logic it would be good
        to implement it on the service level too(in a real world project for example) as its more secure and flexible*/
@Path(ENTRY_PATH)
class UserSecurityCheck(
    private val identity: SecurityIdentity
){
    val roles = listOf(STUDENT, ADMIN, TEACHER)
    val username: String = identity.principal.name
    fun checkRoles(): String {
        return when (roles.firstOrNull { identity.hasRole(it) }) {
            STUDENT -> "Hello, student $username!"
            ADMIN -> "Hello, admin $username!"
            TEACHER -> "Hello, teacher $username!"
            else -> throw ForbiddenException("You do not have the required role to access this resource.")
        }
    }

 /*   @Inject
    lateinit var jsonWebToken: JsonWebToken
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun checkToken(): String {
        return "Access for subject(${jsonWebToken.subject} is granted"
    }*/
}
