package rw.sda.sdahymns.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("SDA Hymns")
                                .description("API documentation for kinyarwanda sda hyms")
                                .version("v1.0")
                                .termsOfService("TOC")
                                .license(new License().name("License").url("#"))
                );
    }
}
