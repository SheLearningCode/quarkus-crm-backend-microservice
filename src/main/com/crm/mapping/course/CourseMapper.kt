package com.crm.mapping.course

import com.crm.mapping.student.StudentDto
import com.crm.mapping.student.StudentMapper
import com.crm.model.CourseEntity
import javax.enterprise.context.ApplicationScoped
/** The CourseMapper class helps us to map an entity to a dto and the way back round,
there are also tools like mapstruct which automate the process,but with more dependencies there comes more responsibility.
It would be very useful though if this class gets a lot bigger to consider a tool like mapstruct */
@ApplicationScoped
class CourseMapper( private val studentMapper: StudentMapper){
    fun courseEntityToDto(courseEntity: CourseEntity): CourseDto {
        return CourseDto(courseEntity.courseId, courseEntity.title, courseEntity.description, courseEntity.category, courseEntity.startDate, courseEntity.maxStudents, courseEntity.students.map { studentMapper.studentEntityToDto(it) } as MutableList<StudentDto>)
    }
    fun courseDtoToEntity(courseDto: CourseDto) : CourseEntity {
        return CourseEntity(courseDto.courseId, courseDto.title, courseDto.description, courseDto.category, courseDto.maxStudents, courseDto.startDate)
    }
}