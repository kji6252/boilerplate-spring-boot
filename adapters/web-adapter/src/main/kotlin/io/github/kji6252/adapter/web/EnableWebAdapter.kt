package io.github.kji6252.adapter.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.env.Profiles
import org.springframework.core.env.StandardEnvironment
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(WebConfig::class)
annotation class EnableWebAdapter

@Configuration
@ComponentScan(
    basePackageClasses = [WebConfig::class],
)
class WebConfig() : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOriginPatterns("localhost")
    }

}
