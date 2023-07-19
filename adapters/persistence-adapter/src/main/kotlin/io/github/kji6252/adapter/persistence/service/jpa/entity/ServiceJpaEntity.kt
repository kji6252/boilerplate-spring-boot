package io.github.kji6252.adapter.persistence.service.jpa.entity

import io.github.kji6252.adapter.persistence.common.jpa.entity.BaseEntity
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Entity
@Table(name = "service")
@Audited
class ServiceJpaEntity(

  @Id
  @Column(nullable = false, length = 50)
  val serviceId: String,

  @Column(nullable = false, length = 50)
  val serviceName: String,

  ) : BaseEntity() {

}
