package br.com.richer.torcedor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket detalheApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.richer.torcedor.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build());

        return docket;
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("API Torcedor");
        apiInfoBuilder.description("Api para realização de um CRUD.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.license("Licença - Open Source");

        return apiInfoBuilder;

    }
}
