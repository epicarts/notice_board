package com.osstem.notice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc // swagger 사용을 위해 추가
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override //security 반영 설정
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .authorizeRequests()
                    .antMatchers("/api/**", "/h2-console/**")
                    .permitAll() //api, h2 모든 권한 허용
                    .antMatchers("/swagger-ui/**","/swagger-ui.html**","/swagger-ui/**", "/configuration/**",
                            "/swagger-resources/**", "/v2/api-docs","/webjars/**", "/webjars/springfox-swagger-ui/*.{js,css}")
                    .permitAll() // swagger 모든 권한 허용
                .and() // csrf disable
                    .csrf().disable()
                    .headers().frameOptions().disable();
    }
}