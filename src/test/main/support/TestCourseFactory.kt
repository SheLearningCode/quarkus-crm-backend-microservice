package main.support

import com.crm.mapping.course.CourseDto
import java.time.LocalDate
import java.util.*

class TestCourseFactory {
    companion object {
        /**
        Creates a random CourseDto.
         */
        fun createTestCourse(
            id: UUID = UUID.randomUUID(),
            title: String = id.toString().substring(2,4),
            description: String = id.toString().substring(1,1),
            category: String = "$title some cat",
            startDate: LocalDate = LocalDate.of(2024, 10, description.toInt()),
            maxStudents: Int = description.toInt()

        ): CourseDto = CourseDto(id, description,category, title, startDate, maxStudents)

        /**
        Creates X-amount of random CourseDtos in a list
         */
        fun createTestCourses(
            amount: Int,
        ): List<CourseDto> = (0 until amount).map { createTestCourse() }
    }
}