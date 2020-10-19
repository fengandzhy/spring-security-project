package org.zhouhy.securityproject.controller;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhouhy.securityproject.dto.User;
import org.zhouhy.securityproject.dto.UserCondition;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value="/me")
    public Object me(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value="/users",method=RequestMethod.POST)
    public User createUser(@RequestBody User user){

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public List<User> finsUsers(@RequestParam(required = false)  String username){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value="/usersByCondition",method=RequestMethod.GET)
    public List<User> finsUsersByCondition(UserCondition userCondition,
                                           @PageableDefault(page=2,size=17,sort="username,desc") Pageable pageable){
        List<User> users = new ArrayList<>();

        System.out.println(ReflectionToStringBuilder.reflectionToString(userCondition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value="/users/{id:\\d+}",method=RequestMethod.GET)
    public User getUserDetail(@PathVariable String id){
        User user = new User();
        user.setUsername("Tom");
        return user;
    }

    @RequestMapping(value="/users",method=RequestMethod.PUT)
    public User updateUser(@Valid @RequestBody User user, BindingResult result){

        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(error->{
                System.out.println(error.getDefaultMessage());
            });
        }

        user.setId("1");
        return user;
    }

    @RequestMapping(value="/users/{id:\\d+}",method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id){
        System.out.println("delete user "+id);
    }
}
