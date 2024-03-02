package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect // 이게 있어야 AOP로 사용가능
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // hello.hellospirng 하위 패키지에 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long strat = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - strat;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }

}
