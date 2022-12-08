package com.bitacademy.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object AroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		/* before */
		// 로딩시간 재보기
		StopWatch sw = new StopWatch();
		sw.start();
				
		
		/* PointCut Method 실행 */
		Object result =  pjp.proceed();
		
		/* after */
		// 로딩시간 결과 도출
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time][" + taskName + "] " + totalTime + "mills");
		
		return result;
	}
}
