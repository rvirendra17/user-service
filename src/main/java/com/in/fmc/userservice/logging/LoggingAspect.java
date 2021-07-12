package com.in.fmc.userservice.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

	@Before(value = "JointPointConfig.methodExecution()")
	public void logMethodEntry(JoinPoint joinPoint) {
		log.debug("In {} : {}()", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
	}

	@AfterReturning(value = "JointPointConfig.methodExecutionReturn()", returning = "returnValue")
	public void logMethodExit(JoinPoint joinPoint, Object returnValue) {

		log.debug("Out {} : {}() is returning - {}", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName(), returnValue.toString());

	}

	@AfterThrowing(value = "JointPointConfig.methodExecutionReturn()", throwing = "exception")
	public void logMethodException(JoinPoint joinPoint, Exception exception) {

		log.debug("{} : {}() is throwing - {}", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName(), exception.getClass().getName());

	}
}
