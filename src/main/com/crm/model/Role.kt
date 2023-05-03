package com.crm.model

object Role {
    const val ADMIN = "admin"
    const val STUDENT = "student"
    const val TEACHER = "teacher"
    // Add additional roles as needed
}

/** By defining the constants in a separate class, we can keep our role
names organized and centralized, making it easier to maintain and update
the application in the future. Additionally, this approach helps to keep the code clean
and readable by avoiding repetition of the role names in multiple classes.

An object in Kotlin is a singleton, meaning that there is only one instance of it in the entire application.
In this case, the Roles object serves as a container for the constants, rather than being a traditional class.
We can use the Roles object in the same way we would use a class, by referring to its properties and constants.
*/