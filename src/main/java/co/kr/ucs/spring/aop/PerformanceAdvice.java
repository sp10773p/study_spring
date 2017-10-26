package co.kr.ucs.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceAdvice {
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		String signature = joinPoint.getSignature().toShortString();
		System.out.println("================= " + signature + " Start");
		long start = System.currentTimeMillis();
		
		try {
			Object result = joinPoint.proceed();
			return result;
		}finally {
			long finish = System.currentTimeMillis();
			System.out.println("================= " + signature + " End (" + (finish - start) + " ms)");
		}
	}
}
