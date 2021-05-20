package org.zhouhy.security.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.zhouhy.security.demo.dto.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Security";
    }

    @RequestMapping(value="/users",method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam("username")String username){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @PostMapping(value="/user")
    public User createUser (@RequestBody User user){
        System.out.println(user.toString());
        return user;
    }

    @RequestMapping(value="/users",method=RequestMethod.POST)
    public User createUser1(@RequestBody User user){

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId(1);
        return user;
    }
}
