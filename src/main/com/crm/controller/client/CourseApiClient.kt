package com.crm.controller.client

import com.crm.controller.Routes.COURSE_PATH
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.Path

@Path(COURSE_PATH)
@RegisterRestClient(configKey = COURSE_PATH)
class CourseApiClient /**(courseServiceInterface: CourseServiceInterface) : CourseController(courseServiceInterface)*/
