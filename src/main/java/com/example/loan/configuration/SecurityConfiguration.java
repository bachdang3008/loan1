package com.example.loan.configuration;
import com.example.loan.reponsitory.UserReponsitory;
import com.example.loan.service.UserDetailService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableAutoConfiguration
@RequiredArgsConstructor
public class Sconfiguration {

    public final UserDetailService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.POST,"/users/post").permitAll()
                        .requestMatchers(HttpMethod.POST,"/interestrate/post").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder() )));

        return http.build();
    }


@Bean
    public JwtDecoder jwtDecoder() {
        String key ="p.ai(mb%ziU>m=cdx@6y}H:-PR{7i]ES:Nv%[?8$0Ax[Vxx(%|*<GIn,Gdz&<[#Moc_eZM[#8rXM}ZBXK6/R{w";

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HS512");

        return NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider authenticationManager = new DaoAuthenticationProvider(userDetailService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationManager);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
