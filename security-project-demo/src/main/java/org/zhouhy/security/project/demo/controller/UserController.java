package org.zhouhy.security.project.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhouhy.security.project.demo.dto.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public List<User> finsUsers(@RequestParam  String username){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
