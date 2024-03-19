package dev.be.async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync // async로 동작할 수 있는 annotation
public class AsyncConfig {
}
