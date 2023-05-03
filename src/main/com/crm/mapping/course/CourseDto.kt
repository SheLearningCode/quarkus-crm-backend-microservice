package com.crm.mapping.course
import com.crm.mapping.ValidationMassages.Companion.DATE_HAS_TO_BE_IN_FUTURE
import com.crm.mapping.ValidationMassages.Companion.IT_HAS_TO_BE_AT_LEAST_ONE_STUDENT
import com.crm.mapping.ValidationMassages.Companion.ONLY_255_SIGNS_ALLOWED
import com.crm.mapping.ValidationMassages.Companion.TITLE_MUST_BE_NOT_BLANK
import com.crm.mapping.student.StudentDto
import org.jboss.resteasy.reactive.DateFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.Id
import javax.validation.constraints.Future
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/* DTOs are data transfer objects which help us to regulate our data transfer.
As with an Entity we have to transfer the whole object with all attributes included, with DTos we can also transfer only some needed attributes,
which makes our performance better and also our data more secure */
data class CourseDto(

    @field:Id
    var courseId: UUID,

    @field:Size(min = 0, max = 35)
    @field:NotNull(message = TITLE_MUST_BE_NOT_BLANK)
    var title: String,

    @field:Size(max=255, message = ONLY_255_SIGNS_ALLOWED)
    var description: String,

    @field:NotNull
    var category: String,

    @DateFormat
    @field:Future(message = DATE_HAS_TO_BE_IN_FUTURE)
    var startDate: LocalDate = LocalDate.now(),

    @field:Size(min=1, max=20)
    @field:NotNull(message = IT_HAS_TO_BE_AT_LEAST_ONE_STUDENT)
    var maxStudents: Int,

    val students: MutableList<StudentDto> = mutableListOf()
    )

