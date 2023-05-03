package com.crm.mapping.student
import com.crm.model.StudentEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentMapper {
    fun studentEntityToDto(studentEntity: StudentEntity): StudentDto {
        return StudentDto( studentEntity.studentId , studentEntity.firstname, studentEntity.lastname, studentEntity.email)
    }
    fun studentDtoToEntity(studentDto: StudentDto) : StudentEntity {
        return StudentEntity( studentDto.studentId , studentDto.firstName, studentDto.lastName, studentDto.email)
    }
}