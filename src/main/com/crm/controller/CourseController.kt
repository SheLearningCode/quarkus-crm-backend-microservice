package com.crm.controller
import com.crm.controller.Routes.COURSES_PATH
import com.crm.controller.Routes.COURSE_PATH
import com.crm.controller.Routes.JOIN_PATH
import com.crm.mapping.course.CourseDto
import com.crm.model.Role.ADMIN
import com.crm.model.Role.STUDENT
import com.crm.model.Role.TEACHER
import com.crm.service.CourseServiceInterface
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Timed
import org.jboss.resteasy.reactive.ResponseStatus
import org.jboss.resteasy.spi.HttpResponseCodes.*
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.validation.Valid
import javax.ws.rs.*

@Path("$COURSE_PATH/")
class CourseController(val courseServiceInterface: CourseServiceInterface) {
 
        /**@Inject
        lateinit var client: CourseApiClient*/
        @POST
        @Path("add")
        @RolesAllowed(ADMIN, TEACHER)
       // @ResponseStatus(SC_CREATED)// http-code = 201
        @ResponseStatus(SC_CREATED)
        @Timed(name = "addMetrics", unit = MetricUnits.MILLISECONDS) //force the smallrye library to collect metrics from JAX-RS endpoints
        fun addNewCourse(@Valid course: CourseDto): CourseDto=
            courseServiceInterface.addNewCourse(course)
        @GET // with @HEAD, we get the same as with @GET accept that head does not have a return body response
        @Path(COURSES_PATH)
        @RolesAllowed(ADMIN, TEACHER)
        @ResponseStatus(SC_OK)// http-code = 200
        fun getAllCourses(): List<CourseDto> =
           courseServiceInterface.getAllCourses()
        @GET
        @Path("{title}")
        @RolesAllowed(ADMIN, TEACHER, STUDENT)
        @ResponseStatus(SC_OK)
        fun findByTitle(@PathParam("title") @Valid title:String) : List<CourseDto> =
            courseServiceInterface.getCoursesByTitle(title)
        @GET
        @Path("{category}")
        @RolesAllowed(ADMIN, TEACHER, STUDENT)
        @ResponseStatus(SC_OK)
        fun findByCategory(@PathParam("category") @Valid category:String): List<CourseDto> =
            courseServiceInterface.getCoursesByCategory(category)
        @PUT
        @Path("{courseId}")
        @RolesAllowed(ADMIN, TEACHER)
        @ResponseStatus(SC_ACCEPTED)// http-code = 202
        fun editCourseById(@PathParam("courseId") courseId : UUID, @Valid courseToUpdate: CourseDto): CourseDto =
            courseServiceInterface.editCourseById(courseId,courseToUpdate)
        @DELETE
        @Path("{courseId}")
        @RolesAllowed(ADMIN, TEACHER)
        @ResponseStatus(SC_NO_CONTENT)//http-code: 204
        fun deleteCourseById(@PathParam("courseId") courseId: UUID){
            courseServiceInterface.deleteCourseById(courseId)
        }
        @POST
        @Path("$JOIN_PATH/{courseId}/{studentId}")
        @RolesAllowed(ADMIN, TEACHER, STUDENT)
        @ResponseStatus(SC_CREATED)
        fun enrollStudentToCourse( @PathParam("courseId") courseId: UUID,
                                   @PathParam("studentId") studentId: UUID
        ): Unit=
            courseServiceInterface.addStudentToCourse(courseId, studentId)
}