package main.support

import com.crm.mapping.student.StudentDto
import java.util.*

class TestStudentFactory {
    companion object {
        /**
          Creates a random StudentDto.
         */
        fun createTestStudent(
            id: UUID = UUID.randomUUID(),
            firstName: String = id.toString().substring(1,5),
            lastName: String = id.toString().substring(5,7),
            email: String = "$firstName.$lastName@web.de"

        ): StudentDto = StudentDto(id, firstName, lastName, email )

        /**
          Creates X-amount of random StudentDtos in a list
         */
        fun createTestStudents(
            amount: Int,
        ): List<StudentDto> = (0 until amount).map { createTestStudent() }
    }
}