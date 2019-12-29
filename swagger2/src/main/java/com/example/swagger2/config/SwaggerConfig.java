package com.example.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 // 开启swagger
public class SwaggerConfig {

    @Bean
    public Docket Docket(Environment environment) {

        // 设置环境
        Profiles profile =  Profiles.of("dev", "test");
        // 判断是否在这些环境中
        boolean flag = environment.acceptsProfiles(profile);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 通过运行环境判断是否开启Swagger
                .enable(flag?true:false)
                .select()
                // RequestHandlerSelectors 配置扫描接口的方式
//                .apis(RequestHandlerSelectors.basePackage("com.example.swagger2.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                // 过滤路径， 只扫描选择路径
                .paths(PathSelectors.ant("/**"))
                .build();

    }

    @Bean
    public Docket Docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("g1");
    }

    @Bean
    public Docket Docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("g2");
    }

    @Bean
    public Docket Docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("g3");
    }

    /**
     * 基本显示信息配置
     * @return apiinfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Test Api Documentation",
                "Test Api Documentation",
                "version - 1.0",
                "http://120.55.165.19:7001",
                new Contact("sblov", "http://120.55.165.19:7001", "sb_lov@sina.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }

}
