package com.crm.model
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentRepository : PanacheRepositoryBase<StudentEntity, UUID>

