package main.com.crm.controller
import com.crm.controller.StudentController
import com.crm.controller.security.UserSecurityCheck
import com.crm.service.StudentService
import io.quarkus.test.junit.QuarkusTest
import main.support.BaseUnitTest
import main.support.TestStudentFactory
import org.apache.http.HttpStatus.SC_OK
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import javax.ws.rs.core.Response
import kotlin.test.assertEquals

@QuarkusTest
/**The @QuarkusTest annotation is part of the Quarkus testing framework and is used
   to run tests in Quarkus environment. It sets up a test environment, starts the required services,
   and automatically deploys the test class as a part of the test. Springboot equivalent: @SpringBootTest*/
class StudentControllerUnitTest : BaseUnitTest() {

    private val studentService = mock(StudentService::class.java)
    private val userSecurityCheck = mock(UserSecurityCheck::class.java)
    private val controller by lazy { StudentController(studentService, userSecurityCheck) }

    @Test
    fun `when getAllStudents is called then it returns a list of all students and status code ok`() {
       /** val testStudents =  listOf(
            StudentDto(UUID.randomUUID(), "Jane", "Doe", "jane.doe@email.com"),
            StudentDto( UUID.randomUUID(), "John", "Doe", "john.doe@email.com")
        )*/
        val testStudents = TestStudentFactory.createTestStudents(5)
        `when`(studentService.getAllStudents()).thenReturn(testStudents)
        val result = controller.getAllStudents()

        assertEquals(Response.Status.OK.statusCode, SC_OK )
        assertEquals(testStudents, result)
    }
    @Test
    fun `when findStudentById is called then it returns a course with give id and status code ok`(){
        val testStudent = TestStudentFactory.createTestStudent()
        val studentId = testStudent.studentId

        `when`(studentService.findStudentById(studentId)).thenReturn(testStudent)
        val result = controller.findStudentById(studentId)

        then(Response.Status.OK.statusCode)
        assertEquals(testStudent, result)
    }

    @Test
    fun ` when fun deleteStudentById is called with given id then it returns status code no content`(){
        val testStudent = TestStudentFactory.createTestStudent()
        val studentId = testStudent.studentId
        `when`(studentService.deleteStudentById(studentId)).thenReturn(Unit)
        (controller.deleteStudentById(studentId))
    }
}