package com.krieger.document.manager.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");

        Info info = new Info();
        info.setTitle("Document Manager API");
        info.description("API to manage documents and their authors and references");

        return new OpenAPI()
                .addServersItem(server)
                .info(info);
    }
}
