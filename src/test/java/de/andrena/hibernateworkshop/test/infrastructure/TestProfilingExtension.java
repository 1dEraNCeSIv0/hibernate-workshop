package de.andrena.hibernateworkshop.test.infrastructure;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.springframework.core.env.Profiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;

import static org.springframework.test.context.junit.jupiter.SpringExtension.getApplicationContext;

@ExtendWith(SpringExtension.class)
public class TestProfilingExtension implements InvocationInterceptor {

    private static final Profiles PROFILING = Profiles.of("profiling");

    @Override
    public void interceptTestMethod(
            Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext)
            throws Throwable {

        invocation.proceed();

        var totalRepetitions = 1;
        printProfilingInfo(extensionContext, totalRepetitions);
    }

    @Override
    public void interceptTestTemplateMethod(
            Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext)
            throws Throwable {

        invocation.proceed();

        var totalRepetitions = invocationContext.getExecutable().getAnnotation(RepeatedTest.class).value();
        printProfilingInfo(extensionContext, totalRepetitions);
    }

    private void printProfilingInfo(ExtensionContext extensionContext, int totalRepetitions) {
        if (profilingIsActive(extensionContext)) {
            var stopWatchControl = getStopWatchControl(extensionContext);
            stopWatchControl.printTaskExecutionTime();
            stopWatchControl.handleLastRepetition(totalRepetitions);
        }
    }

    private boolean profilingIsActive(ExtensionContext extensionContext) {
        return getApplicationContext(extensionContext).getEnvironment().acceptsProfiles(PROFILING);
    }

    private StopWatchControl getStopWatchControl(ExtensionContext extensionContext) {
        return getApplicationContext(extensionContext).getBean(StopWatchControl.class);
    }

}
