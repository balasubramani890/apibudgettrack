package com.budgettrack.api.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectExecution {

	@Before("execution(* com.budgettrack.budgettrackapi.Controller.*.*(..))")
	public void logBeforeMethodExecution(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Calling Controller Class Name: " + className + " & Method Name:" + methodName);
	}

	@Before("execution(* com.budgettrack.budgettrackapi.Service.*.*(..))")
	public void logControllerMethods(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Calling Service Class Name: " + className + " & Method Name:" + methodName);
	}

	@Before("execution(* com.budgettrack.budgettrackapi.Repository.*.*(..))")
	public void logServiceMethods(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Calling Repository Class Name: " + className + " & Method Name:" + methodName);
	}
	
}