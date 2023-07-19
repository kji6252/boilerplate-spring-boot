package io.github.kji6252.adapter.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.TypeExcludeFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType


/**
 * ApiApplication의 각종 Enable Annotation이 스프링테스트 진행 시 불필요한 mock을 발생하여 추가함
 */
@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(excludeFilters = [ComponentScan.Filter(type = FilterType.CUSTOM, classes = [TypeExcludeFilter::class])])
class Config {

  @PersistenceContext
  private val entityManager: EntityManager? = null

  @Bean
  fun jpaQueryFactory(): JPAQueryFactory? {
    return JPAQueryFactory(entityManager)
  }
}
