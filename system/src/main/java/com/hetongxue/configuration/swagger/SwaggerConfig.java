package com.hetongxue.configuration.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 描述：swagger 配置类
 *
 * @author hy
 * @version 1.0
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfig {

    @Bean
    public Docket initDocket(Environment env) {
        Profiles profile = Profiles.of("test", "dev");
//        boolean flag = env.acceptsProfiles(profile);
        boolean flag = true;
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(flag).select().apis(RequestHandlerSelectors.basePackage("com.hetongxue.system.controller")).paths(PathSelectors.any()).build().groupName("第一组");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("学生选课系统").termsOfServiceUrl("https://blog.csdn.net/qq_14818715?spm=1000" + ".2115.3001.5343").description("这是一个学生选课系统").contact(new Contact("何同学", "https://blog.csdn.net/qq_14818715?spm=1000.2115.3001.5343", "heyue.chongqing@aliyun.com")).version("V1.0").license("Apache 2.0").build();
    }
}