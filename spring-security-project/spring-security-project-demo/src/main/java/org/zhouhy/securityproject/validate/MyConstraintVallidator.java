package org.zhouhy.securityproject.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.zhouhy.securityproject.annotation.MyConstraint;
import org.zhouhy.securityproject.service.HelloService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintVallidator implements ConstraintValidator<MyConstraint,Object> {

    @Autowired
    private HelloService helloService;

    //初始化
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validate init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(helloService.greet("Sam"));
        return false;
    }
}
