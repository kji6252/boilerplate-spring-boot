package io.github.kji6252.adapter.persistence.service.jpa.repository

import io.github.kji6252.adapter.persistence.service.jpa.entity.ServiceJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceJpaRepository : JpaRepository<ServiceJpaEntity, String>, CustomServiceJpaRepository {
}


interface CustomServiceJpaRepository {
//  fun findByAccountName(accountName: String): List<String>
}

//class CustomAccountJpaRepositoryImpl(private val jpaQueryFactory: JPAQueryFactory) : CustomAccountJpaRepository {
//  override fun findByAccountName(accountName: String): List<String> {
//
////    val tsnUser = QTsnUser.tsnUser
////    return jpaQueryFactory
////      .select(tsnUser.ciIdk)
////      .from(tsnUser)
////      .where(tsnUser.ciIdk.eq(ciIdk))
////      .fetch()
//  }

