package ActifyAssignment.userManagement.config;

import ActifyAssignment.userManagement.utils.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/v1/User/add","/api/v1/User/get","/api/v1/User/{id}","/api/v1/roles/add",
                                "/api/v1/User/*/assign-role/*","/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/tasks/assign").hasRole("MANAGER")
                        .requestMatchers("/api/v1/user/**").hasAnyRole("USER", "ADMIN", "MANAGER") // Users can access their APIs


                        .anyRequest().authenticated()

                )
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }


}
