package com.crm.exception
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper
import javax.ws.rs.BadRequestException
import javax.ws.rs.core.Response
class ExceptionMapping {
    /** the @ServerExceptionMapper annotation allows quarkus to map our exceptions automatically and access it globally
    and with the CommonError class we define the error
    Exceptions should always be logged!
    TODO: implement global exception mapper, to catch all the exceptions that may occur in the project
     */
    @ServerExceptionMapper
    fun mapNoSuchElementException(ex: NoSuchElementException): RestResponse<CommonErrors> =
        RestResponse.status(
            Response.Status.NOT_FOUND,
            CommonErrors(
                error = ex.localizedMessage,
                status = 404 //TODO: better writing and reserving own status codes & CustomExceptions, that are globally accessible
            )
        )
    @ServerExceptionMapper
    fun mapBadRequestException(ex: BadRequestException): RestResponse<CommonErrors> =
        RestResponse.status(
            Response.Status.BAD_REQUEST,
            CommonErrors(
                error = ex.localizedMessage,
                status = 400
            )
        )
}