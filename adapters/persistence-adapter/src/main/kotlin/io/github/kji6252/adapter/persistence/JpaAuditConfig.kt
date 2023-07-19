package io.github.kji6252.adapter.persistence

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditConfig {

  @Bean
  fun auditorProvider(): AuditorAware<String> {
    return AuditorAwareImpl()
  }

}

class AuditorAwareImpl : AuditorAware<String> {
  override fun getCurrentAuditor(): Optional<String> {
    val requestAttributes = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)
    requestAttributes ?: return Optional.of(ANONYMOUS_USER)
    val userId: String? = requestAttributes.request.getHeader(USERID)
    if (userId.isNullOrEmpty()) return Optional.of(ANONYMOUS_USER)
    return Optional.of(userId);
  }

  companion object {
    const val ANONYMOUS_USER = "anonymous"
    const val USERID = "USERID"
  }
}