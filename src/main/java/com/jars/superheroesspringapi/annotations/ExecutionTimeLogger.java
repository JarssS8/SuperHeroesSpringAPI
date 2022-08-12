package com.jars.superheroesspringapi.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeLogger {

    Logger logger = LoggerFactory.getLogger(ExecutionTimeLogger.class);

    @Around("@annotation(com.jars.superheroesspringapi.annotations.ExecutionTime)")
    public Object timeToExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Execution time for {}: {} ms", proceedingJoinPoint.getSignature(), (endTime - startTime));
        return obj;
    }
}
