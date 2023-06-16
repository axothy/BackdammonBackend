package ru.axothy.backdammon.playerservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("player-service")
@Getter
@Setter
public class ServiceConfig {
    private String property;

}
