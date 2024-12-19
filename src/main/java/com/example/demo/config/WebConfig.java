package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.filter.AuthFilter;
import com.example.demo.filter.RoleFilter;
import com.example.demo.interceptor.AdminRoleInterceptor;
import com.example.demo.interceptor.AuthInterceptor;
import com.example.demo.interceptor.UserRoleInterceptor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    // TODO: 2. 인가에 대한 이해
    private static final String[] AUTH_REQUIRED_PATH_PATTERNS = {"/users/logout", "/admins/*", "/items/*"};
    private static final String[] USER_ROLE_REQUIRED_PATH_PATTERNS = {"/reservations/*"};
    private static final String[] ADMIN_ROLE_REQUIRED_PATH_PATTERNS = {"/admins/*"};

    private final AuthInterceptor authInterceptor;  // 로그인 여부를 확인하는 인터셉터
    private final UserRoleInterceptor userRoleInterceptor;  // user 권한을 확인하는 인터셉터
    private final AdminRoleInterceptor adminRoleInterceptor;    // admin 권한을 확인하는 인터셉터
    private final EntityManager entityManager;

    // 인터셉터의 우선 순위와 path 패턴 적용.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(AUTH_REQUIRED_PATH_PATTERNS)
                .order(Ordered.HIGHEST_PRECEDENCE);

        registry.addInterceptor(userRoleInterceptor)
                .addPathPatterns(USER_ROLE_REQUIRED_PATH_PATTERNS)
                .order(Ordered.HIGHEST_PRECEDENCE + 2);

        registry.addInterceptor(adminRoleInterceptor)
                .addPathPatterns(ADMIN_ROLE_REQUIRED_PATH_PATTERNS)
                .order(Ordered.HIGHEST_PRECEDENCE + 1);
    }

    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns(AUTH_REQUIRED_PATH_PATTERNS);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean userRoleFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new RoleFilter(Role.USER));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
        filterRegistrationBean.addUrlPatterns(USER_ROLE_REQUIRED_PATH_PATTERNS);
        return filterRegistrationBean;
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}