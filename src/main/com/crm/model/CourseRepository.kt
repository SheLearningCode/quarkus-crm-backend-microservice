package com.crm.model

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
/**chose to extend to PanacheRepositoryBase, so I have two
 parameters CourseEntity and UUID with PanacheRepository only, there is only one parameter: CourseEntity*/
class CourseRepository: PanacheRepositoryBase<CourseEntity, UUID> {
    fun getCourseByTitle(title :String): List<CourseEntity> {
        return list("title = ?1", title)
    }
    fun getCourseByCategory(category : String):List<CourseEntity> {
        return list("category = ?1", category)
    }
}