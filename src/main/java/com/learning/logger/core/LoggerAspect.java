package com.learning.logger.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggerAspect implements AroundEvent{

    private static final Logger logger = LogManager.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(com.learning.logger.annotation.Logger)")
    public void around(){

    }

    @Override
    @Around("around()")
    public Object around(ProceedingJoinPoint joinPoint) throws Exception {
        long startTime = System.currentTimeMillis();

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage(), e);
        }

        printLog(joinPoint, startTime);
        return result;
    }

    private void printLog(ProceedingJoinPoint joinPoint, long startTime) {
        try {
            Class clazz = getClass(joinPoint);
            Method method = getMethod(joinPoint);
            String methodName = method.getName();
            if (logger.isInfoEnabled()) {
                logger.info("{}#{}-执行耗时:{}s", clazz.getSimpleName(), methodName, (System.currentTimeMillis() - startTime) / 1000.0);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }
}
