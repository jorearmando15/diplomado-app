package co.diplomado.iudigital.helpmeiud.app.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Helpme IUD")
                        .description("API Rest HelpMe IUDigital")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .name("Julio Martinez")
                                        .email("julio.martinez@iudigital.edu.co")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("IU Digital")
                        .url("https://iudigital.edu.co"));
    }
}
