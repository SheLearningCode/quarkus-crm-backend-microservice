package com.crm.controller
import com.crm.controller.Routes.STUDENTS_PATH
import com.crm.controller.Routes.STUDENT_PATH
import com.crm.controller.security.UserSecurityCheck
import com.crm.mapping.student.StudentDto
import com.crm.model.Role.ADMIN
import com.crm.model.Role.STUDENT
import com.crm.model.Role.TEACHER
import com.crm.service.StudentServiceInterface
import org.jboss.resteasy.reactive.ResponseStatus
import org.jboss.resteasy.spi.HttpResponseCodes.*
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.validation.Valid
import javax.ws.rs.*

/** we can inject other classes in the constructor like below or with @Inject annotation, both ways are equivalent
 always using val when the value never changes like in the constructor
 better using @Consumes/@Produces JSON in bigger application: explicit white listing approach  */
@Path(STUDENT_PATH)
class StudentController(val studentServiceInterface: StudentServiceInterface, val userSecurityCheck: UserSecurityCheck) {
    /** TODO: -naming: in Quarkus:Resource, Controller is more Springboot-Style/old-fashioned
             -the scope has to be as small as possible, better implementing @transactional on the service level
             -implement everywhere the userSecurityCheck when finished with keycloak configuration
             -important: SWAGGER UI is not best practice at Nova-tec, so better delete and user other documentation frameworks/tools
    @Inject
    lateinit var  client: StudentApiClient */
    @GET
    @Path("$STUDENTS_PATH/")
    @RolesAllowed(ADMIN, TEACHER)
    @ResponseStatus(SC_OK) // it's more readable to write the @ResponseStatus annotation, then to return a response in the fun
    fun getAllStudents(): List<StudentDto> = userSecurityCheck.checkRoles().run{//checking the @RolesAllowed
        studentServiceInterface.getAllStudents()
    }
    @GET
    @Path("{studentId}")   // if the path continues after {student_id} we need to declare it here too f.e. /title
    @RolesAllowed(ADMIN, TEACHER)
    @ResponseStatus(SC_OK)
    fun findStudentById(@PathParam("studentId") studentId: UUID): StudentDto= // functional programming makes the code readable and cleaner
        studentServiceInterface.findStudentById(studentId)
    @POST
    @Path("add")
    @RolesAllowed(ADMIN, TEACHER)
    @ResponseStatus(SC_CREATED)
    fun addNewStudent(@Valid student: StudentDto): StudentDto =
        studentServiceInterface.addNewStudent(student)

    @DELETE
    @Path("{studentId}")
    @RolesAllowed(TEACHER, ADMIN)
    @ResponseStatus(SC_NO_CONTENT)
    fun deleteStudentById(@PathParam("studentId") studentId: UUID){
        studentServiceInterface.deleteStudentById(studentId)
    }
    /** we could use @Patch to only partially update our object, so that not the whole object has to be updated like with the @PUT
    which makes our app faster but the negative point is that we can't use validations then anymore properly, also we would need to create a JsonObject
    where we also would need a PatchDto for example where we don't map all attributes but only partially*/
    @PUT
    @Path("{studentId}")
    @RolesAllowed(STUDENT, ADMIN)
    @ResponseStatus(SC_ACCEPTED)// with @PathParam, we are passing an attribute to our path:  /{studentId} for example
    fun editStudentById(@PathParam("studentId") studentId: UUID, @Valid studentToUpdate: StudentDto): StudentDto =
        studentServiceInterface.editStudentById(studentId, studentToUpdate)
}



