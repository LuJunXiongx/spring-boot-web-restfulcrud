package com.lujunxiong.springboot.cofig;

import com.lujunxiong.springboot.component.LoginHandlerInterceptor;
import com.lujunxiong.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * @author lujunxiong
 * 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
 */
@Configuration
//@EnableWebMvc  不要接管SpringMvc

public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);

        //浏览器发送 /atguigu 请求来到 success

        registry.addViewController("/atguigu").setViewName("success");
    }


    /**所有的WebMvcConfigurerAdapter组件都会一起起作用
     * 将组件祖册在容器中
     * @return
     */
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController( "/" ).setViewName( "login" );
                registry.addViewController( "/index.html" ).setViewName( "login" );
                registry.addViewController( "/main.html" ).setViewName( "dashboard" );

            }
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源
                //SpringBoot已经做好静态资源映射
                registry.addInterceptor( new LoginHandlerInterceptor() ).addPathPatterns( "/**" )
                        .excludePathPatterns( "/index.html","/","/user/login" );//拦截任意请求,除了index.html，/，/user/login
            }
        };
        return adapter;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
