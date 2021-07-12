package com.in.fmc.userservice.logging;

import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JointPointConfig {

	@Pointcut("execution(* com.in.fmc.userservice.controllers.*.*(..))"
			+ "||execution(* com.in.fmc.userservice.services.*.*(..))"
			+ "||execution(* com.in.fmc.userservice.exceptionhandlers.*.*(..))"
			+ "||execution(* com.in.fmc.userservice.utils.*.*(..))")
	public void methodExecution() {
		log.trace("methodExecution");
	}

	@Pointcut("execution(* com.in.fmc.userservice.controllers.*.*(..))"
			+ "||execution(* com.in.fmc.userservice.services.*.*(..))"
			+ "||execution(* com.in.fmc.userservice.exceptionhandlers.*.*(..))"
			+ "||execution(* com.in.fmc.userservice.utils.*.*(..))")
	public void methodExecutionReturn() {
		log.trace("methodExecutionReturn");
	}

}
