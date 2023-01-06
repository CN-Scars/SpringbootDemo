package com.example.springbootdemo.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

//书写关于登录的内容
//把所有和操作用户相关的接口放在一起，共用一个一级路径
@Controller
@RequestMapping("/user")
@ResponseBody
public class user {
    //操作数据库：SQL语句
    @GetMapping("/login")
    public Map<String, Object> login(String username, String password) throws Exception {  //抛异常
        //对返回的对象进行实例化
        Map<String, Object> map = new HashMap<>();
        System.out.println(username);
        System.out.println(password);
        //用jdbc去利用代码去数据库中操作SQL语句
        //注册数据库驱动
        //要先进行防爆处理（抛异常）
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=UTF-8", "root", "password");  //连接地址、用户名、密码
        //预编译————用SQL语句去完成编译
        PreparedStatement statement = connection.prepareStatement("select * from accounts where USER = ? and CURRENT_CONNECTIONS = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        //接收结果，executeQuery()方法只针对查询使用，executeUpdate()用于增删改
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
        {
            map.put("msg", "登录成功");
            map.put("code", "208");
        }
        else
        {
            map.put("msg", "登录失败");
            map.put("code", "508");
        }
//        while (resultSet.next())    //遍历数据库某个字段的数据
//        {
//            System.out.println(resultSet.getString("USER"));
//        }
//        map.put("msg", "test");
        return map;
    }
}