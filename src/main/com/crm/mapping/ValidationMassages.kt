package com.crm.mapping

internal class ValidationMassages {
    companion object {
        const val ONLY_255_SIGNS_ALLOWED ="The description can only be 255 letters long"
        const val IT_HAS_TO_BE_AT_LEAST_ONE_STUDENT = "It has to be at least 1 student, and max of 35 students"
        const val FIRSTNAME_MUST_BE_NOT_BLANK = "firstname must not be blank"
        const val LASTNAME_MUST_BE_NOT_BLANK = "lastname must not be blank"
        const val EMAIL_MUST_BE_NOT_BLANK = "email must not be blank"
        const val TITLE_MUST_BE_NOT_BLANK = "title must not be blank"
        const val DATE_HAS_TO_BE_IN_FUTURE = "Start date has to be in future"
        const val EMAIL_MUST_BE_VALID = "It has to be a valid E-Mail."
    }
}