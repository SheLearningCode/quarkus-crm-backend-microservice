package com.crm.controller.client

import com.crm.controller.Routes.STUDENT_PATH
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.Path

@Path(STUDENT_PATH)
@RegisterRestClient(configKey = STUDENT_PATH)
class StudentApiClient
