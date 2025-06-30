package com.watcher.config;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JedisConfig.class)
@ComponentScan(basePackages = "com.watcher")
public class AppConfig {


}
