package com.eazybytes.EazySchoolApplication.config;

//commit
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    //by remove csrf.disable(), csrf protection  activate by spring
    //csrf.ignoringRequestMatchers( routes) used to ignore the csrf protection for given routes
    //csrf protection work for put ,put and delete not for get mapping
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

      //  http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers(PathRequest.toH2Console()))
       //ignoringRequestMatchers(PathRequest.toH2Console()) not needed as connecting with mysql not h2 database
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg").
                        ignoringRequestMatchers("/public/**").
                        ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
                                .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                                .requestMatchers("/closeMsg/**").hasRole("ADMIN").
                        requestMatchers("/admin/**").hasRole("ADMIN").
                        requestMatchers("/api/**").authenticated().
                        requestMatchers("/", "/home").permitAll().
                        requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                                .requestMatchers("/displayProfile").authenticated()
                                .requestMatchers("/updateProfile").authenticated().
                                requestMatchers("/student/**").authenticated()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll().
                        requestMatchers("/login").permitAll().
                        requestMatchers("/logout").permitAll().
                                requestMatchers("/public/**").permitAll()
//                        requestMatchers(PathRequest.toH2Console()).permitAll()

                )
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());
     //   not needed as connecting with mysql not h2 database
      //  http.headers(headersConfigurer -> headersConfigurer
       //         .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));


        return http.build();
    }

    //not needed as implement our own authentication provider which check the authentication from database
//
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user").password("12345").roles("USER").build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin").password("54321").roles("ADMIN","USER").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}