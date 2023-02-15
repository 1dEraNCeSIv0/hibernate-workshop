package de.andrena.hibernateworkshop.test.infrastructure;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class TestPointcuts {

    @Pointcut("execution(* de.andrena.hibernateworkshop.service.*.*Service.*(..))")
    void allServiceMethods() {}

}
