package io.github.kji6252.adapter.persistence.common.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@Audited
abstract class BaseEntity {
  @CreatedDate
  @Column(nullable = false, updatable = false)
  var createdAt: LocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())

  @LastModifiedDate
  @Column(nullable = false)
  var modifiedAt: LocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())

  @CreatedBy
  @Column(nullable = false, updatable = false)
  var createdBy: String? = null

  @LastModifiedBy
  @Column(nullable = false)
  var modifiedBy: String? = null

}
