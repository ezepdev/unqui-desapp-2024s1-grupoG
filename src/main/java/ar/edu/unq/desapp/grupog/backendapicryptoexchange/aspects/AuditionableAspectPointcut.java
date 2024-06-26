package ar.edu.unq.desapp.grupog.backendapicryptoexchange.aspects;


import io.swagger.v3.core.util.Json;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(0)
public class AuditionableAspectPointcut {

    static final Logger logger = LogManager.getLogger(AuditionableAspectPointcut.class);


    @Pointcut("execution(* ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers.*.*(..))")
    public void auditionnable() {
    }

    @Around("auditionnable()")
    public Object aroundAuditionableMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();

        if (logger.isInfoEnabled()) logger.info("timestamp call method: {}",  new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));

        logRequest(pjp, pjp.getSignature().toString());
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        if (logger.isInfoEnabled()) logger.info("\nexecuted time: {}ms", endTime - startTime);
        if (logger.isInfoEnabled()) logger.info("<- {} Response: \n{}\n", methodName, Json.pretty(result));

        return result;
    }


    private void logRequest(ProceedingJoinPoint jp, String serviceName) {
        if (logger.isInfoEnabled()) logger.info("{} CALL:", serviceName);
        String[] argNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Object[] values = jp.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (argNames.length != 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], values[i]);
            }
        }
        if (logger.isInfoEnabled() && !params.isEmpty()){
            logger.info("-> {} Request", jp.getSignature().getName());
            logger.info("with params: {}",Json.pretty(params));
    }
    }
}

