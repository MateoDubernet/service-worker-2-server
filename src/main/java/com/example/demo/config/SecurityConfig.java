package com.example.demo.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.Security;

@Configuration
public class SecurityConfig {
    @PostConstruct
    public void init() {
        Security.addProvider(new BouncyCastleProvider());
    }
}
