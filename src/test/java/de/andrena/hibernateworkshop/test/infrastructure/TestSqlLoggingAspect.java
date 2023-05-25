package de.andrena.hibernateworkshop.test.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("sql-logging")
public class TestSqlLoggingAspect {

    private LoggingSystem loggingSystem;

    public TestSqlLoggingAspect(LoggingSystem loggingSystem) {
        this.loggingSystem = loggingSystem;
    }

    @Around("TestPointcuts.allServiceMethods()")
    Object showSqlLogs(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            sqlLogLevel(LogLevel.TRACE);
            return proceedingJoinPoint.proceed();
        } finally {
            sqlLogLevel(LogLevel.INFO);
        }
    }

    private void sqlLogLevel(LogLevel level) {
        loggingSystem.setLogLevel("org.hibernate.SQL", level);
    }

}
