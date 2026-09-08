package com.example.loan.configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.GET,"Loan/get").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder() )));

        return http.build();
    }

    public JwtDecoder jwtDecoder() {
        String key ="p.ai(mb%ziU>m=cdx@6y}H:-PR{7i]ES:Nv%[?8$0Ax[Vxx(%|*<GIn,Gdz&<[#Moc_eZM[#8rXM}ZBXK6/R{w";

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HS512");

        return NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

}
