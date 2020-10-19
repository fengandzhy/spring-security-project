package org.zhouhy.securityproject.service.impl;

import org.springframework.stereotype.Service;
import org.zhouhy.securityproject.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greet(String name) {
        return "hello "+name;
    }
}
