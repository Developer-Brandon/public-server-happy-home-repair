package com.dev.webApp.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
//public class LogAdvice {
//
//    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
//
//    @Before("execution(* com.dev.webApp.service.*(..))")
//    public void logBefore() {
//        logger.info("=========================");
//    }
//}
