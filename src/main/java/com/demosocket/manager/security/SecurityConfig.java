package com.demosocket.manager.security;

import com.demosocket.manager.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

import static com.demosocket.manager.security.SecurityConstants.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final Environment environment;

    @Autowired
    public SecurityConfig(DataSource dataSource, Environment environment) {
        this.dataSource = dataSource;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(USERS_URL)
                .hasAuthority(Role.COMMANDER.toString())
                .antMatchers(SYSTEMS_ADD_URL, SYSTEMS_DELETE_URL, SYSTEMS_EDIT_URL, TASK_EDIT_URL, INFLUENCE_UPDATE_URL)
                .hasAnyAuthority(Role.COMMUNIST.toString(), Role.COMMANDER.toString())
                .antMatchers(SIGNUP_URL, INFORMATION_URL, STYLES_URL, IMAGES_URL)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .formLogin()
                .loginPage(LOGIN_URL)
                .permitAll()
                .and()
            .logout()
                .permitAll();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(environment.getProperty("spring.queries.users-query"))
                .authoritiesByUsernameQuery(environment.getProperty("spring.queries.roles-query"))
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
