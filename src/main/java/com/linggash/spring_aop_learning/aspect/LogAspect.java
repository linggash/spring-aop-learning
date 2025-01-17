package com.linggash.spring_aop_learning.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("target(com.linggash.spring_aop_learning.service.HelloService)")
    public void helloServiceMethod() {
    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before {}.{}()", className, methodName);
    }

    @Around("helloServiceMethod()")
    public Object aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        try {
            log.info("Around Before {}.{}()", className, methodName);
            return joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable){
            log.info("Around Error {}.{}()", className, methodName);
            throw throwable;
        }finally {
            log.info("Around Finally {}.{}()", className, methodName);

        }
    }

    @Pointcut("execution(* com.linggash.spring_aop_learning.service.HelloService.*(java.lang.String))")
    public void pointCutHelloServiceStringParam(){
    }

//    @Before("pointCutHelloServiceStringParam()")
//    public void logStringParameter(JoinPoint joinPoint){
//        String value = (String) joinPoint.getArgs()[0];
//        log.info("Execute method with parameter : {}", value);
//    }

    @Before("pointCutHelloServiceStringParam() && args(name)")
    public void logStringParameter(String name){
        log.info("Execute method with parameter : {}", name);
    }

    @Pointcut("execution(* com.linggash.spring_aop_learning.service.*.*(..))")
    public void pointCutServicePackage(){

    }

    @Pointcut("bean(*Service)")
    public void pointcutServiceBean(){

    }

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod(){

    }

    @Pointcut("pointCutServicePackage() && pointcutServiceBean() && pointcutPublicMethod()")
    public void publicMethodForService(){

    }

    @Before("publicMethodForService()")
    public void logAllPublicServiceMethod(){
        log.info("Log for all public service method");
    }
}
