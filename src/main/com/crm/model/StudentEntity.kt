package com.crm.model
import com.crm.model.Schema.DEFAULT_SCHEMA
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "student_entity", schema= DEFAULT_SCHEMA)
class StudentEntity
  (
   @Column(name="student_id")
   @field:Id
   @GeneratedValue
   val studentId: UUID = UUID.randomUUID(),

   @Column(name = "firstname", nullable = false)
   @field:Size(min = 0, max = 20)
   var firstname: String= " ",

   @Column(name = "lastname", nullable = false)
   @field:Size(min = 0, max = 20)
   var lastname: String= " ",

   @Column(name= "email", nullable = false, unique = true)
   @field:Email
   @field:NotBlank
   var email: String= " ",

   @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
   /** LAZY-LOADED: This tells Hibernate to load the relationship only
   when it is actually used. This can help improve the performance of
   the application by preventing unnecessary data from being loaded.

   Usage @EmbeddedId: EmbeddedId is used to map a composite primary key class
   hat is used as the primary key of an entity. The composite primary key
   class must be annotated with @Embeddable and the mapping class must
   have an instance variable of the composite primary key class annotated with @EmbeddedId.

   join table can be omitted unless we want to define special name for the table and columns */
   @JoinTable(
    name = "students_courses",
    joinColumns = [JoinColumn(name = "course_id")],
    inverseJoinColumns = [JoinColumn(name="student_id")],
   )
   // jsonIgnore so we can avoid constant reciprocal callings
   @JsonIgnore
   val courses: MutableList<CourseEntity> = mutableListOf()

   ) : BaseEntity<UUID>()



