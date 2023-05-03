package com.crm.model

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@RegisterForReflection // for native image mode
open class BaseEntity<UUID>(
    @Id open val id: java.util.UUID? = null
) : PanacheEntityBase(){
    override fun toString(): String = "BaseEntity($id)"
    final override fun hashCode(): Int = id.hashCode()
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity<*>) return false
        return id == other.id
    }
}