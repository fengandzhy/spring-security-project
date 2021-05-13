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
}
