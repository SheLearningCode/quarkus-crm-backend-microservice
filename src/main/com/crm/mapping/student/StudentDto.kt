package com.crm.mapping.student
import com.crm.mapping.ValidationMassages.Companion.EMAIL_MUST_BE_NOT_BLANK
import com.crm.mapping.ValidationMassages.Companion.EMAIL_MUST_BE_VALID
import com.crm.mapping.ValidationMassages.Companion.FIRSTNAME_MUST_BE_NOT_BLANK
import com.crm.mapping.ValidationMassages.Companion.LASTNAME_MUST_BE_NOT_BLANK
import com.crm.mapping.course.CourseDto
import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class StudentDto(
    @field:Id
    val studentId: UUID,

    @field:Size(min = 0, max = 20)
    @field:NotNull(message = FIRSTNAME_MUST_BE_NOT_BLANK)
    var firstName: String,

    @field:Size(min = 0, max = 20)
    @field:NotNull(message = LASTNAME_MUST_BE_NOT_BLANK)
    var lastName: String,

    @Email(message = EMAIL_MUST_BE_VALID)
    @field:NotBlank(message = EMAIL_MUST_BE_NOT_BLANK)
    @Column(unique = true)
    var email: String,

    val courses: MutableList<CourseDto> = mutableListOf()
    )
