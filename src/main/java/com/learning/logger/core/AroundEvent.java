package com.learning.logger.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public interface AroundEvent {

    Object around(ProceedingJoinPoint joinPoint) throws Exception;

    default Class getClass(JoinPoint joinPoint){
        return joinPoint.getTarget().getClass();
    }

    default Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature sig = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return getClass(joinPoint).getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }
}
