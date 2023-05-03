package com.crm.service

import com.crm.mapping.course.CourseDto
import com.crm.mapping.course.CourseMapper
import com.crm.mapping.student.StudentMapper
import com.crm.model.CourseRepository
import com.crm.model.StudentEntity
import com.crm.model.StudentRepository
import java.time.LocalDate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.ws.rs.BadRequestException

/**1. '.map'  maps and wraps other functions giving a list as return type
   2. '.let' is used to apply a function to a value, and then return the result of that function.
        It is typically used in combination with the Optional class, which is used to represent values that may or may not be present.
   3. 'apply' is used in combination with the Optional class. It is similar to the .let method,
        but it is used to change the value contained in the Optional instead of returning a new value.*/
@ApplicationScoped
class CourseService(
    private val courseRepository: CourseRepository,
    private val studentRepository: StudentRepository,
    private val courseMapper: CourseMapper,
    private val studentMapper: StudentMapper
): CourseServiceInterface {
    override fun getAllCourses() = courseRepository.listAll().map { courseMapper.courseEntityToDto(it) }
    override fun getCoursesByTitle(title: String): List<CourseDto> =
        courseRepository.getCourseByTitle(title).map { courseMapper.courseEntityToDto(it) }

    override fun getCoursesByCategory(category: String): List<CourseDto> =
        courseRepository.getCourseByCategory(category).map { courseMapper.courseEntityToDto(it) }
    @Transactional //@transactional annotation is needed when ever we change the data in our db so for: PUT, POST, PATCH
    override fun addNewCourse(course: CourseDto): CourseDto =
        courseMapper.courseDtoToEntity(course).let {
            if (it.startDate.isBefore(LocalDate.now())) {
                throw BadRequestException("you can't create a course with an old date")
            } else {
                courseRepository.persist(it)
                courseMapper.courseEntityToDto(it)
            }
        }

    override fun deleteCourseById(courseId: UUID) {
        courseRepository.findById(courseId)?.let {
            courseRepository.deleteById(courseId)
        } ?: throw NoSuchElementException("There is no course with this ID: $courseId")
    }
    @Transactional
    override fun editCourseById(courseId: UUID, courseToUpdate: CourseDto): CourseDto =
        courseRepository.findById(courseId)?.let { course ->
            course.title = courseToUpdate.title
            course.category = courseToUpdate.category
            course.description = courseToUpdate.description
            course.startDate = courseToUpdate.startDate
            course.maxStudents = courseToUpdate.maxStudents
            course.students = (courseToUpdate.students)
                .map { studentMapper.studentDtoToEntity(it) } as MutableList<StudentEntity>
            courseRepository.persist(course)
            courseMapper.courseEntityToDto(course)
        } ?: throw BadRequestException("One of the fields is null/blank")

    @Transactional
    override fun addStudentToCourse(courseId: UUID, studentId: UUID) {
        studentRepository.findById(studentId)?.let { student ->
            courseRepository.findById(courseId)?.let { course ->
                course.apply {
                    students.add(student)
                    courseRepository.persist(this)
                }
                student.apply {
                    courses.add(course)
                    studentRepository.persist(this)
                }
            }
        } ?: throw NoSuchElementException("Student or course not found")
    }

}
/*
This was the code before I implemented functional programming as above:

@ApplicationScoped
class CourseService(private var courseRepository: CourseRepository,
                    private var studentRepository: StudentRepository, private var courseMapper: CourseMapper, private var studentMapper: StudentMapper
) {
    fun getAllCourses(): List<CourseDto>? {
        val courseList = courseRepository.listAll()
        return courseList.map {courseMapper.courseEntityToDto(it)}
        }
        fun getCoursesByTitle(title: String): List<CourseDto> {
            val courses = courseRepository.getCourseByTitle(title)
            if (courses == emptyList<CourseEntity>()) {
                throw IllegalArgumentException("There is no course with this title $title")
            }
            return courses.map { courseMapper.courseEntityToDto(it) }
        }

        fun getCoursesByCategory(category: String): List<CourseDto> {
            val courses = courseRepository.getCourseByCategory(category)
            if (courses == emptyList<CourseEntity>()) {
                throw IllegalArgumentException("There is no course with this category $category")
            }
            return courses.map{courseMapper.courseEntityToDto(it)}
        }

        fun addNewCourse(course: CourseDto): CourseDto {
            val courseMapped = courseMapper.courseDtoToEntity(course)
            courseRepository.persist(courseMapped)

            if (courseMapped.startDate.isBefore(LocalDate.now())) {
                throw IllegalArgumentException("you can't create a course with an old date")
            } else if (courseMapped.title.isBlank() ||
                courseMapped.category.isBlank() ||
                courseMapped.description.isBlank() ||
                courseMapped.maxStudents == 0
            ) {
                throw IllegalArgumentException("one of the fields is blank")
            }
            return course
        }

    fun deleteCourseById(courseId: UUID): Boolean {
            if (courseId == courseRepository.findById(courseId)?.courseId) {
                return courseRepository.deleteById(courseId)
            } else throw IllegalArgumentException("There is no course with this ID: $courseId")
        }

        fun editCourseById(courseId: UUID, courseUpdate: CourseDto): CourseDto {
            val course = courseRepository.findById(courseId)
            if (courseId === course.courseId) {
                course.title = courseUpdate.title
                course.category = courseUpdate.category
                course.description = courseUpdate.description
                course.startDate = courseUpdate.startDate
                course.maxStudents = courseUpdate.maxStudents
                course.students = (courseUpdate.students).map { studentMapper.studentDtoToEntity(it) } as MutableList<StudentEntity>
            } else throw IllegalArgumentException("There is no course with this ID:  $courseId")
            return courseUpdate
        }

        fun addStudentToCourse(courseId: UUID, studentId: UUID) {
            val student = studentRepository.findById(studentId)
            val course = courseRepository.findById(courseId)

            if (course != null && student != null) {
                val students = course.students
                val courses = student.courses
                students.add(student)
                courses.add(course)
                course.students = students
                student.courses.add(course)
                courseRepository.persist(course)
                studentRepository.persist(students)
            }
        }

} */



