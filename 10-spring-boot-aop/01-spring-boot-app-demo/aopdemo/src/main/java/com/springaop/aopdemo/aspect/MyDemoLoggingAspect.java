package com.springaop.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging
    // let's starts with an @Before advice
    //run this code before this method(name: pointcut expression)
    //first * mean any class
    //2 * mean any method
    //0 or more ane params
    //execution(* com.springaop.aopdemo.dao.*.*(..))
    @Before("execution(* com.springaop.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("\n===========>> Executing @Before advice on addAccount() ");
    }
}
