package com.ndgndg91.todo.configuration;

import com.ndgndg91.todo.component.TodoUserDetailsService;
import com.ndgndg91.todo.component.PersistentRememberMeTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.ndgndg91.todo.Role.ADMIN;
import static com.ndgndg91.todo.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TodoUserDetailsService todoUserDetailsService;

    private final PersistentRememberMeTokenRepository persistentRememberMeTokenRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(todoUserDetailsService).passwordEncoder(this.bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.rememberMe()
                .key("ndgndg91-/V`&fKLh]Hp+8/_6-Z4a)Prg:M%:")
                .tokenValiditySeconds(3600)
                .tokenRepository(persistentRememberMeTokenRepository)
                .and().authorizeRequests()
//                .antMatchers("/resource/**").permitAll()
                .antMatchers("/join/form").permitAll()
                .antMatchers("/").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/admin/*").access("hasRole('ADMIN') and isFullyAuthenticated()")
                .and().formLogin()
                        .loginPage("/login/form")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login/form?error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                .and().httpBasic()
                .and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login/form?logout")
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
