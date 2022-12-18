package com.example.springbootdemo.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

//注解，是给机器看的
@Controller //控制类，处理http请求
//设置一级路径
@RequestMapping("/user")
public class userController {
    //设置二级路径，同时设置请求方式
    @GetMapping("/test")
    public void test() {
        System.out.println("访问了/user/test  ");
    }
    @GetMapping("/test2")
    //接口若有返回值，就必须加上一个注解
    @ResponseBody
    public String test1() {
        return "114514";
    }
    @GetMapping("/homo")
    @ResponseBody
    public List<String> homo1() {
        //实例化一个List集合
        List<String> strings = new ArrayList<>();
        strings.add("114514");
        strings.add("1919");
        strings.add("0810");
        return strings;
    }
    @GetMapping("/test3")
    @ResponseBody
    public Map<String,Object> test2() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","1919");
        return map;
    }
}