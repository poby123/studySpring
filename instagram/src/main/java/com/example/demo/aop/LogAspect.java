package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {
    
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        LogExecutionTime logExecutionTimeAnnotation = method.getAnnotation(LogExecutionTime.class);
        String targetName = logExecutionTimeAnnotation.targetName();

        StopWatch stopWatch = new StopWatch(targetName);
        stopWatch.start();

        // @LogExecutionTime이 붙어있는 타겟 메서드를 실행
        Object targetProceed = joinPoint.proceed();

        stopWatch.stop();
        log.info("Total execution time of {} : {} ms", targetName, stopWatch.getTotalTimeMillis());

        return targetProceed;
    }
}
