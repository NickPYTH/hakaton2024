package de401t.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
        .select()//
        .apis(RequestHandlerSelectors.any())//
        .paths(Predicates.not(PathSelectors.regex("/error")))//
        .build()//
        .apiInfo(metadata())//
        .useDefaultResponseMessages(false)//
        .securitySchemes(Collections.singletonList(apiKey()))
        .securityContexts(Collections.singletonList(securityContext()))
        .tags(
                new Tag("users", "Операции с пользователями"),
                new Tag("roles", "Операции с ролями"),
                new Tag("priority", "Операции с ролями"),
                new Tag("requests", "Операции с заявками"),
                new Tag("statuses", "Операции со статусами"),
                new Tag("subtypes", "Операции с подтипами типов заявок"),
                new Tag("types", "Операции с типами заявок"),
                new Tag("groups", "Операции с группами"),
                new Tag("reports", "Формирование отчетов")
        )//
        .genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
        .title("Hakaton 2024")//
        .description("Hakaton API")//
        .version("1.0.0")//
        .contact(new Contact(null, null, "nicksrnk@gmail.com"))//
        .build();
  }
  
  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any())
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
  }

}
