package main.com.crm.controller
import com.crm.controller.CourseController
import com.crm.service.CourseServiceInterface
import io.quarkus.test.junit.QuarkusTest
import main.support.BaseUnitTest
import main.support.TestCourseFactory
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import javax.ws.rs.core.Response
import kotlin.test.assertEquals
/** using @Nested and inner classes helps us organize and sort our endpoints and tests,
 so we can find them easier for example. Inner classes should be named as the fun api endpoint for example getById.
 The test fun names should exactly tell what happens in the test fun and what is the outcome
 for readability and test cleanness */

@QuarkusTest
class CourseControllerUnitTest : BaseUnitTest(){

    private val courseService = mock(CourseServiceInterface::class.java)
    private val controller = CourseController(courseService)
        @Test
        fun `when post is called then it returns a 201 status code and the added course`() {
            val testCourse= TestCourseFactory.createTestCourse()
            `when`(courseService.addNewCourse(testCourse)).thenReturn(testCourse)
            val result = controller.addNewCourse(testCourse)

            assertEquals(testCourse, result)
            verify(courseService).addNewCourse(testCourse)// we need 'verify' whenever we persist something, so the service is tested too.
        }
    @Nested
    inner class GetById {
        @Test
        fun `when get is called then it returns a list of all courses and status code 200`() {
            val testCourses = TestCourseFactory.createTestCourses(6)

            `when`(courseService.getAllCourses()).thenReturn(testCourses)
            val result = controller.getAllCourses()

            assertEquals(testCourses, result)
        }
        @Test
        fun `when get is called then it returns status code 200 and the course found by title`() {
            val testCourses = TestCourseFactory.createTestCourses(2)

            `when`(courseService.getCoursesByTitle("Test Course")).thenReturn(testCourses)
            val result = controller.findByTitle("Test Course")

            assertEquals(testCourses, result)
        }

        @Test
        fun `when get is called then it returns 200 status code and the course found by given category`() {
            val testCourses = TestCourseFactory.createTestCourses(3)

            `when`(courseService.getCoursesByCategory("Test Category")).thenReturn(testCourses)
            val result = controller.findByCategory("Test Category")

            assertEquals(testCourses, result)
        }
    }
        @Test
        fun `when put is called then it returns status code 201 and the edited course found by given id`() {
            val testCourseToUpdate= TestCourseFactory.createTestCourse()
            val courseId = testCourseToUpdate.courseId

            `when`(courseService.editCourseById(courseId, testCourseToUpdate)).thenReturn(testCourseToUpdate)
            controller.editCourseById(courseId, testCourseToUpdate)

            verify(courseService).editCourseById(courseId, testCourseToUpdate)
        }

        @Test
        fun `when delete is called it returns status code 204 and the deleted course found by given id`(){
            val testCourse= TestCourseFactory.createTestCourse()
            val courseId = testCourse.courseId

            `when`(courseService.deleteCourseById(courseId)).thenReturn(Unit)
            controller.deleteCourseById(courseId)

            assertEquals(Response.Status.NO_CONTENT.statusCode, HttpStatus.SC_NO_CONTENT)
        }
    }
