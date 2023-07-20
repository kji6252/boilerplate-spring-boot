package io.github.kji6252.adapter.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.data.envers.repository.config.EnableEnversRepositories
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Import(
  DbConfig::class,
  JpaAuditConfig::class,
)
annotation class EnablePersistenceAdapter()

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["io.github.kji6252.adapter.persistence.*.jpa.repository"],
  entityManagerFactoryRef = "entityManager",
  transactionManagerRef = "transactionManager",
  repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class
)
@EnableEnversRepositories
@ComponentScan(basePackageClasses = [DbConfig::class])
class DbConfig {


  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.db.$DATASOURCE_WRITER")
  fun dbWriterDataSource(): DataSource = DataSourceBuilder.create().build()

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.db.$DATASOURCE_READER")
  fun dbReaderDataSource(): DataSource = DataSourceBuilder.create().build()


  @Primary
  @FlywayDataSource
  @Bean
  fun dbDataSource(): DataSource {

    val writerDataSource: DataSource = dbWriterDataSource()
    val readerDataSource: DataSource = dbReaderDataSource()

    val dataSourceMap = mutableMapOf<Any, Any>()
    dataSourceMap[DATASOURCE_WRITER] = writerDataSource
    dataSourceMap[DATASOURCE_READER] = readerDataSource

    val replicationRoutingDataSource = ReplicationRoutingDataSource()
    replicationRoutingDataSource.setDefaultTargetDataSource(writerDataSource)
    replicationRoutingDataSource.setTargetDataSources(dataSourceMap)
    replicationRoutingDataSource.afterPropertiesSet()

    return LazyConnectionDataSourceProxy(replicationRoutingDataSource)

  }

  @Bean
  fun transactionManager(entityManager: LocalContainerEntityManagerFactoryBean) =
    JpaTransactionManager(entityManager.`object`!!)

  @Primary
  @Bean
  fun entityManager(
    jpaProperties: JpaProperties,
    hibernateProperties: HibernateProperties,
  ): LocalContainerEntityManagerFactoryBean {
    val entityManagerFactoryBuilder = EntityManagerFactoryBuilder(
      HibernateJpaVendorAdapter(),
      hibernateProperties.determineHibernateProperties(jpaProperties.properties, HibernateSettings()),
      null
    )
    return entityManagerFactoryBuilder
      .dataSource(dbDataSource())
      .packages("io.github.kji6252.adapter.persistence.*.jpa.entity")
      .build()
  }

  @Bean
  fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
    return JPAQueryFactory(entityManager)
  }
}

private const val DATASOURCE_WRITER = "writer"
private const val DATASOURCE_READER = "reader"

class ReplicationRoutingDataSource : AbstractRoutingDataSource() {

  override fun determineCurrentLookupKey(): Any? {
    if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
      return DATASOURCE_READER
    }
    return DATASOURCE_WRITER
  }
}