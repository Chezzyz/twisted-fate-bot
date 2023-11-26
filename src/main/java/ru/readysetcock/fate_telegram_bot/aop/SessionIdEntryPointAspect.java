package ru.readysetcock.fate_telegram_bot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Aspect
@Configuration
public class SessionIdEntryPointAspect {

    public static final String SESSION_ID_TAG = "sid";

    @Around("@annotation(ru.readysetcock.fate_telegram_bot.aop.SessionIdEntryPoint)" +
            "|| @within(ru.readysetcock.fate_telegram_bot.aop.SessionIdEntryPoint)")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        if (MDC.get(SESSION_ID_TAG) != null) {
            return joinPoint.proceed();
        }

        try {
            UUID uuid = UUID.randomUUID();
            MDC.put(SESSION_ID_TAG, uuid.toString());
            return joinPoint.proceed();
        } finally {
            MDC.remove(SESSION_ID_TAG);
        }
    }
}
