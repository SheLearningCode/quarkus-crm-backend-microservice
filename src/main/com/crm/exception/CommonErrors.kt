package com.crm.exception
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CommonErrors(
    val id: UUID? = null,
    val error: String, // error msg
    val status: Int // HTTP Status Code
    )