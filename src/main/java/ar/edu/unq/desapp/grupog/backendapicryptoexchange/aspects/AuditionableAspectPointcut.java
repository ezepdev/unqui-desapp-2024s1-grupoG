package ar.edu.unq.desapp.grupog.backendapicryptoexchange.aspects;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.JWTService;
import io.swagger.v3.core.util.Json;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(0)
public class AuditionableAspectPointcut {

    static final String[] pointcut = {};
    static final Logger logger = LogManager.getLogger(AuditionableAspectPointcut.class);


    @Pointcut("execution(* ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers.*.*(..))")
    public void auditionnable() {
    }

    @Around("auditionnable()")
    public Object aroundAuditionableMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();

        logger.info("timestamp call method: " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));

        //logger.info("method call: " + pjp.getSignature().getName());
        //logger.info("method class with parameters: " + Arrays.toString(pjp.getArgs()));
        logRequest(pjp, pjp.getSignature().toString());
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("execution time: " + (endTime - startTime) + " ms");


        logger.info("\nResponse time: " + (endTime - startTime) + "ms", true);
        logger.info("<- " + methodName + " Response: \n" + Json.pretty(result) + "\n", true);

        return result;
    }


    private void logRequest(ProceedingJoinPoint jp, String serviceName) {
        logger.info(serviceName + " CALL:", true);
        String[] argNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Object[] values = jp.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (argNames.length != 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], values[i]);
            }
        }
        logger.info("-> " + jp.getSignature().getName() + " Request", true);
        if (!params.isEmpty()) logger.info(Json.pretty(params), true);
    }

    @Before("auditionnable()")
    public void beforeAuditionableMethod() {
        logger.info("before auditionnable method");
    }

    @After("auditionnable()")
    public void afterAuditionableMethod() {
        logger.info("after auditionnable method");
    }

}

