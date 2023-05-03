package com.crm.model
import com.crm.model.Schema.DEFAULT_SCHEMA
import org.jboss.resteasy.reactive.DateFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "course_entity", schema = DEFAULT_SCHEMA)
class CourseEntity(
    /** @Column annotations can be omitted except the table columns should have different names then our variable,
       I left it because of readability and to make sure that the columns in the migration script match the entity*/
    @Id
    @Column(name="course_id")
    @GeneratedValue
    val courseId: UUID = UUID.randomUUID(),

    @Column(name = "title", nullable = false)
    @field:Size(min = 1, max = 35)
    var title: String ="",

    @Column(name = "category", nullable = false)
    @field:Size(max=255)
    var category: String ="",

    @Column(name = "description")
    @field:Size(max=255)
    var description: String="",

    @Column(name = "max_students", nullable = false)
    @field:Size(min=1, max=20)
    var maxStudents : Int ,

    @Column(name = "start_date")
    @DateFormat
    var startDate: LocalDate = LocalDate.now(),

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    var students: MutableList<StudentEntity> = ArrayList()
) : BaseEntity<UUID>()
