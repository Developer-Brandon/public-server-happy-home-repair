package com.dev.webApp.config.aop;

import lombok.extern.slf4j.*;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAdvice {

    @Before("execution(* com.dev.webApp.service.*(..))")
    public void logBefore() {

        log.info("=======================");
    }
}
