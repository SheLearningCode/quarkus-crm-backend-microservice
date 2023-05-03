package com.crm.service

import com.crm.mapping.student.StudentDto
import com.crm.mapping.student.StudentMapper
import com.crm.model.StudentRepository
import org.jboss.logging.Logger.getLogger
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.ws.rs.BadRequestException

@ApplicationScoped
class StudentService(
    private val studentRepository: StudentRepository,
    private val studentMapper: StudentMapper
): StudentServiceInterface{
    private val logger = getLogger(javaClass)
    override fun getAllStudents(): List<StudentDto>{
    val students = studentRepository.listAll().map(studentMapper::studentEntityToDto)
    logger.info("GOT_ALL_STUDENTS [$students]")
    return students
    }
    override fun findStudentById(studentId: UUID): StudentDto = studentRepository.findById(studentId)
        ?.let(studentMapper::studentEntityToDto)
        ?: throw  NoSuchElementException("No student found for id: '$studentId'")
    @Transactional /** the scope has to be as small as possible, better using transactional on the service level
                       with @transactional we keep holding the attached scope*/
    override fun addNewStudent(student: StudentDto): StudentDto = studentMapper.studentDtoToEntity(student).let {
        studentRepository.persist(it)
        return studentMapper.studentEntityToDto(it)
    }

    override fun deleteStudentById(studentId: UUID){
        studentRepository.findById(studentId)?.let {
            studentRepository.deleteById(studentId)
        }?: throw NoSuchElementException("There is no course with this ID: $studentId")
    }
    @Transactional
    override fun editStudentById(studentId: UUID, studentToUpdate: StudentDto): StudentDto =
        studentRepository.findById(studentId)?.let { student ->
            student.firstname = studentToUpdate.firstName
            student.lastname = studentToUpdate.lastName
            student.email = studentToUpdate.email
            studentRepository.persist(student)
            studentMapper.studentEntityToDto(student)
        }?:let {
            throw BadRequestException("Some of the attributes may be null or invalid")
        }
    }

/*
This was before rewriting the code to functional programing as above:

fun addNewStudent(student: StudentDto): StudentDto {
       val studentMapped = studentMapper.studentDtoToEntity(student)
       studentRepository.persist(studentMapped) // TODO: implement Try-Catch to check  service
       return studentMapper.studentEntityToDto(studentMapped)
   }
     fun deleteStudentById(studentId: UUID): Boolean {
       if (studentRepository.findById(studentId) != null) {
           return studentRepository.deleteById(studentId)
       } else throw IllegalArgumentException("The student with the studentId $studentId does not exist")
   }
   fun editStudentById(studentId: UUID, studentToUpdate: StudentDto): StudentDto {
   val student = studentRepository.findById(studentId)
   val studentMapped = studentMapper.studentEntityToDto(student)
       if (studentId === studentMapped.studentId) {
           studentMapped.firstName = studentToUpdate.firstName
           studentMapped.lastName = studentToUpdate.lastName
           studentMapped.email = studentToUpdate.email
       } else throw IllegalArgumentException("There is no Participant with this ID $studentId")
           return studentMapped
       }
   } */