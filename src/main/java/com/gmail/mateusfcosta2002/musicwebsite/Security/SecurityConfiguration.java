package com.gmail.mateusfcosta2002.musicwebsite.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Services.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()
                .requestMatchers(("/logout")).authenticated()
                .anyRequest().permitAll()
            )

            .httpBasic(basic -> basic.disable())

            .formLogin(form -> form.disable())

            //.logout((logout) -> logout  
            //    .logoutUrl("/logout")
            //    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
            //
            .logout(logout -> logout.disable())

            .csrf(csrf -> csrf.disable())

            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(WebProperties.USER_ROLE_PREXIX);
    }

    @Bean 
    ProviderManager providerManager(PasswordEncoder encoder, AppUserDetailsService appUserDetailsService) {
        var daoProvider = new DaoAuthenticationProvider(encoder);
        daoProvider.setUserDetailsService(appUserDetailsService);
        var manager = new ProviderManager(daoProvider);

        return manager;
    }

    //@Bean
    //UserDetailsManager userDetailsManager(DataSource dataSource) {
    //    var manager = new JdbcUserDetailsManager(dataSource);
    //    manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").build());
    //    return manager;
    //}

    //@Bean 
    //AuthenticationManager authenticationManager(PasswordEncoder encoder) {
    //    var dao = new DaoAuthenticationProvider(encoder);
    //    dao.setUserDetailsPasswordService(new AppUserDetailsService());
    //    return new ProviderManager(dao);
    //}
}
