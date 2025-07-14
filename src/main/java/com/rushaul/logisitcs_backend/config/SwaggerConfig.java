package com.rushaul.logisitcs_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Logistics API",
                version = "1.0",
                description = "APIs for managing users, orders, hubs, and delivery assignments"
        )
)
public class SwaggerConfig {}
