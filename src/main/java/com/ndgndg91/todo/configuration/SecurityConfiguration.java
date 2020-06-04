package com.ndgndg91.todo.configuration;

import com.ndgndg91.todo.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("john@ndgndg91.com")
                .password("{noop}john-ndgndg91").roles(Role.USER.name())
        .and().withUser("admin").password("{noop}admin").roles(Role.ADMIN.name());
//        .and().passwordEncoder(this.bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/resource/**").permitAll()
                .antMatchers("/").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/admin/*").hasRole(Role.ADMIN.name())
                .and().formLogin()
                        .loginPage("/login/form")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login/form?error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                .and().httpBasic()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login/form?logout")
                .and().csrf().disable();
    }

//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
