package main
import com.crm.mapping.course.CourseDto
import com.crm.model.CourseEntity
import com.crm.model.CourseRepository
import com.crm.service.CourseService
import com.crm.service.CourseServiceInterface
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import main.support.TestContainerLifeCycleManager
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate
import java.util.*
import javax.inject.Inject
import javax.validation.Validator
import javax.ws.rs.core.MediaType


/** With @QuarkusTest, the test runs in the same process as the tested application,
 so you can inject the application's beans into the test instance etc.,
  while with @QuarkusIntegrationTest, the tested application runs in an external process,
  so you can only interact with it over network. **/

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTestResource(TestContainerLifeCycleManager::class, restrictToAnnotatedClass = true)
// @TestProfile(MainServiceIntTestsWithMockKExtensionProfile::class) // Only needed as MainServiceTest exists several times
    class CourseIntegrationTest{

 // TODO: 1. Unit Tests für Entity um validation zu prüfen,(mit @QuakusTest ist man automatisch im 8081 Port
 // TODO: 2. Integrationstest mit der Reihenfolge machen: POST, GET, PUT, DELETE, Dummy Testdaten,
 // TODO: 3. mit @Nested teste ich den Service; AAA-Prinzip immer verwenden: (given, when, then)

 }
    @Inject
    private lateinit var courseServiceInterface: CourseServiceInterface
    @InjectMockKs
    private lateinit var courseService: CourseService

    private val courseRepositoryMock = mockk<CourseRepository>()
    @Inject
    private lateinit var validator: Validator

 @Test
 fun getAllCoursesEndpoint(){
     val uuid = UUID.randomUUID()
     val course = CourseEntity(
         uuid, "Linux", "Anwendungsentwicklung", "advanced only", 20,
         LocalDate.of(2023, 1, 15), mutableListOf()
     )
     courseRepositoryMock.persist(course)

     given()
         .contentType(MediaType.APPLICATION_JSON)
         .`when`().get("/course-api/")
         .then()
         .statusCode(200)

 }
    @Test
    fun `test findByTitle endpoint`() {
        // Given
        val uuid = UUID.randomUUID()
        val course = CourseEntity(
            uuid, "Anwendungsentwicklung", "Anwendungsentwicklung", "advanced of", 20, 
            LocalDate.of(2023, 1, 15), mutableListOf()
        )
        val uuid2= UUID.randomUUID()
        val course2 = CourseEntity(uuid2, "Web Development", "some description here",
            "category of example",10,  LocalDate.of(2023, 10, 23), mutableListOf())

        courseRepositoryMock.persist(course2)
        courseRepositoryMock.persist(course2)
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(course.title)
            .`when`().get("/courses/title/Web Development")
            .then()
            .statusCode(200)
    }



 @Test
 fun getByCategoryEndpoint(){
     given()
         .`when`().get("/course-api/category/Anwendungsentwicklung")
         .then()
             .statusCode(200)

 }
 @Test
 fun addEndpoint(){
    val uuid = UUID.randomUUID()
     val course = CourseDto(
         uuid,
         "Quarkus", "Frameworks", "some random description", LocalDate.of(2026, 6, 30),23)
     given()
         .contentType(MediaType.APPLICATION_JSON)
         .body(course)
         .`when`().post("/Course/add")
         .then()
             .statusCode(201)
 }

 @Test
 fun deleteByIdEndpoint(): Unit {
     given()
         .`when`().delete("/Course/id")
         .then()
             .statusCode(204)

 }


