package com.crm.service

import com.crm.mapping.course.CourseDto
import java.util.*

interface CourseServiceInterface {
    fun getAllCourses(): List<CourseDto>
    fun getCoursesByTitle(title:String): List<CourseDto>
    fun getCoursesByCategory(category: String): List<CourseDto>
    fun addNewCourse(course: CourseDto): CourseDto
    fun deleteCourseById(courseId: UUID)
    fun editCourseById(courseId: UUID, courseToUpdate: CourseDto): CourseDto
    fun addStudentToCourse(courseId: UUID, studentId: UUID)
}