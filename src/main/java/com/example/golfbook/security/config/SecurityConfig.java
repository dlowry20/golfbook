package com.example.golfbook.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Primary
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration a = new RedisStandaloneConfiguration();
        //a.setPassword(redisPassword);
        //a.setDatabase(0);
        a.setPort(6379);
        a.setHostName("localhost");

        //LettuceClientConfiguration c = LettuceClientConfiguration.builder().useSsl().build();

        return new LettuceConnectionFactory(a);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
//            }
//        };
//    }
}