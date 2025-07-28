package com.rachael.api.wallet.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rachael.api.wallet.constant.APIConstant;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title(APIConstant.OPENAPI_TITLE)
                .version(APIConstant.OPENAPI_VERSION)
                .description(APIConstant.OPENAPI_DESCRIPTION))
            .components(new Components()
                .addSecuritySchemes(APIConstant.SECURITY_REQUIREMENT_NAME,
                    new SecurityScheme()
                        .name(APIConstant.OPENAPI_SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(APIConstant.OPENAPI_SECURITY_SCHEME)
                        .bearerFormat(APIConstant.OPENAPI_BEARER_FORMAT)))
            .addSecurityItem(new SecurityRequirement().addList(APIConstant.SECURITY_REQUIREMENT_NAME));
    }
}