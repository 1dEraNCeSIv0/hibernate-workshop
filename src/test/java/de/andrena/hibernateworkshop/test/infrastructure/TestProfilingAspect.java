package de.andrena.hibernateworkshop.test.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("profiling")
public class TestProfilingAspect {

    private StopWatchControl stopWatchControl;

    public TestProfilingAspect(StopWatchControl stopWatchControl) {
        this.stopWatchControl = stopWatchControl;
    }

    @Around("TestPointcuts.allServiceMethods()")
    Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            stopWatchControl.start(proceedingJoinPoint.getSignature().toShortString());
            return proceedingJoinPoint.proceed();
        } finally {
            stopWatchControl.stop();
        }
    }

}
