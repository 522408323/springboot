package com.lyx.houtai.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("jdbb-session").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        ParameterBuilder signPar = new ParameterBuilder();
        signPar.name("jdbb-sign").description("签名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(signPar.build());
        ParameterBuilder tstampPar = new ParameterBuilder();
        tstampPar.name("jdbb-tstamp").description("时间戳").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tstampPar.build());
        ParameterBuilder rnumPar = new ParameterBuilder();
        rnumPar.name("jdbb-rnum").description("随机数").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(rnumPar.build());
        ParameterBuilder osPar = new ParameterBuilder();
        osPar.name("os").description("设备信息").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(osPar.build());
        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lyx.houtai"))
                .paths(PathSelectors.any())
                .build();
                //.globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("")
                //.termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}

