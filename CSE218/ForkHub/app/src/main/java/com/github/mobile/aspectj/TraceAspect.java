package com.github.mobile.apectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ericfa on 3/6/17.
 */

@Aspect
public class TraceAspect {
    private static Logger log = null;


    @Pointcut ("call(* *.*(..))")
    void anyCall() {}

    @AfterThrowing("anyCall()")
    public void errHandle(JoinPoint jp){
        Log.d("thisthisthisthis", "thisthisthis");

        if(jp.getThis() != null){
            log = Logger.getLogger(String.valueOf(jp.getThis().getClass()));
        }
        else{
            log = Logger.getLogger(String.valueOf(TraceAspect.class));
        }
        log.log(Level.SEVERE, String.valueOf(jp.getThis().getClass()) + "new exception, details at: ", new Throwable());


    }
    /*private static final String POINTCUT_METHOD =
            "execution(@org.android10.gintonic.annotation.DebugTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@org.android10.gintonic.annotation.DebugTrace *.new(..))";

    private static Logger log = null;

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{

        Log.d("thisthisthisthis", "thisthisthis");

        Object result = joinPoint.proceed();

        if(joinPoint.getThis() != null){
            log = Logger.getLogger(String.valueOf(joinPoint.getThis().getClass()));
        }
        else{
            log = Logger.getLogger(String.valueOf(TraceAspect.class));
        }
        log.log(Level.SEVERE, String.valueOf(joinPoint.getThis().getClass()) + "new exception, details at: ", new Throwable());
        return result;
    }
    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append("ForkHub --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }*/
}
