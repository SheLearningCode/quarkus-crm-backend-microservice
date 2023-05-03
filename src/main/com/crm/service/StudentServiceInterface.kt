package com.crm.service

import com.crm.mapping.student.StudentDto
import java.util.*

interface StudentServiceInterface {
    fun getAllStudents(): List<StudentDto>
    fun findStudentById(studentId: UUID):StudentDto
    fun addNewStudent(student: StudentDto):StudentDto
    fun deleteStudentById(studentId: UUID)
    fun editStudentById(studentId: UUID, studentToUpdate: StudentDto): StudentDto
}