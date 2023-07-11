package com.project.trainingteam.config;

import com.project.trainingteam.config.security.AccessControlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    //    @Autowired
//    private AccessControlInterceptor accessControlInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(accessControlInterceptor)
//                .excludePathPatterns("/api/auth/token")
//                .excludePathPatterns("/api/letter-file/download/**")
//                .excludePathPatterns("/api/notification-file/download/**")
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/swagger-ui/**")
//                .excludePathPatterns("/v3/api-docs/**")
//                .excludePathPatterns("/v3/api-docs.yaml");
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");

    }
}
