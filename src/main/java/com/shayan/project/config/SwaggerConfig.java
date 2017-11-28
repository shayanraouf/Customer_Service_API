package com.shayan.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Value("${spring.application.title}")
    private String appTitle;

    @Value("${spring.application.description}")
    private String appDesc;

    @Value("${spring.application.version}")
    private String appVersion;

    @Value("${spring.application.serviceurl}")
    private String appServiceUrl;

    @Value("${spring.application.license}")
    private String appLicense;

    @Value("${spring.application.licenseurl}")
    private String appLicenseUrl;

    @Value("${spring.application.contact.name}")
    private String appContactName;

    @Value("${spring.application.contact.url}")
    private String appContactUrl;

    @Value("${spring.application.contact.email}")
    private String appContactEmail;

    @Value("${service.namespace}")
    private String namespace;

    @Bean
    public Docket getDocket() {

        List<ResponseMessage> globalResponseMessages = new ArrayList<>();

        globalResponseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("Error")
                .responseModel(new ModelRef("ErrorMessage"))
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage ("com.shayan.project"))
                .paths(PathSelectors.regex(namespace + "/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponseMessages)
                .globalResponseMessage(RequestMethod.PUT, globalResponseMessages)
                .globalResponseMessage(RequestMethod.PATCH, globalResponseMessages)
                .globalResponseMessage(RequestMethod.DELETE, globalResponseMessages);
    }

    private ApiInfo getApiInfo () {
        return new ApiInfoBuilder()
                .title(appTitle)
                .description(appDesc)
                .termsOfServiceUrl(appServiceUrl)
                .license(appLicense)
                .licenseUrl(appLicenseUrl)
                .version(appVersion)
                .contact(new Contact(appContactName, appContactUrl, appContactEmail))
                .build();
    }
}

