package de.andrena.hibernateworkshop.testinfrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.boot.logging.LoggerGroups;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestLoggingAspect {

    @Autowired
    private LoggingSystem loggingSystem;

    @Autowired
    private LoggerGroups loggerGroups;

    @Pointcut("execution(* de.andrena.hibernateworkshop.service.*.*Service.*(..))")
    private void allServiceMethods() {}

    @Around("allServiceMethods()")
    Object showSqlLogs(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("THIS IS CALLED");
        sqlLogLevel(LogLevel.DEBUG);
        Object result = proceedingJoinPoint.proceed();
        sqlLogLevel(LogLevel.INFO);
        return result;
    }

    private void sqlLogLevel(LogLevel level) {
        LoggerGroup loggers = loggerGroups.get("sql");
        loggers.configureLogLevel(level, loggingSystem::setLogLevel);
    }

}
