package com.lujunxiong.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author lujunxiong
 */
@Controller
public class HelloController {
    /*@RequestMapping({"/","/index.xml"})
    public String index(){
        return "index";
    }*/

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello controller";
    }


    /**
     * 查出一些数据，在页面展示
     * @param map
     * @return
     */
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        //classpath:/templates/success.html
        map.put( "hello","<h1>你好</h1>" );
        map.put( "users", Arrays.asList("张三","lisi","wangwu") );
        return "success";
    }
}
