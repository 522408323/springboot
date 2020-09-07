package com.lyx.houtai.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截
 */
@Slf4j
@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        // 排除配置
        addInterceptor.excludePathPatterns("/swagger-**"
                ,"/webjars/**"
                ,"/test/get"
                ,"/sss/get"
                ,"/test/by/pid/get/phone"
                ,"/my/tt/login/token/info"
        );
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        /**
         * 请求前拦截
         * @param request
         * @param response
         * @param handler
         * @return
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
           return true;
        }

        /**
         * 请求后拦截
         * @param request
         * @param response
         * @param handler
         * @param modelAndView
         * @throws Exception
         */
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//            log.basicinfo("请求后拦截：{}",request);
            super.postHandle(request, response, handler, modelAndView);
        }
    }
}
