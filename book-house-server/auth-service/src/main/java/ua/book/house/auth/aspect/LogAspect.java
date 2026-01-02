package ua.book.house.auth.aspect;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LogAspect {

    @After("execution(public void ua.book.house.auth..*.init())")
    public void afterInitPostConstructLogAdvice(JoinPoint joinPoint) {
        log.info("Initialize bean {}", joinPoint.getClass().getName());
    }


    @Before("execution(* ua.book.house.auth.controller..*(..)) || " +
            "execution(* ua.book.house.auth.service..*(..)) || " +
            "execution(* ua.book.house.auth.dao..*(..))")
    public void beforeStartAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        log.info("Start method {} of class {}, with those params: {}", methodName, className, Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* ua.book.house.auth.controller..*(..)) || " +
            "execution(* ua.book.house.auth.service..*(..)) || " +
            "execution(* ua.book.house.auth.dao..*(..))")
    public void afterStartAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        log.info("End method {} of class {} was successful", methodName, className);
    }

    @AfterThrowing(value = "execution(* ua.book.house.auth.controller..*(..)) || " +
            "execution(* ua.book.house.auth.service..*(..)) || " +
            "execution(* ua.book.house.auth.dao..*(..))", throwing = "throwable")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        log.error("Error in method {} of class {}: {}", methodName, className, throwable.getStackTrace());
    }
}
