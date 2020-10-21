package online.pandm.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lenovo
 */
@Aspect
@Component
public class LoggingAopUtil {

    private static final Logger logger= LoggerFactory.getLogger(LoggingAopUtil.class);

    /**
     * @MethodName: log
     * @Description: 配置切点
     * @Author: Peng Lu
     * @Data: 2020/7/19 14:22yyy
     */
    @Pointcut("execution(public * online.pandm.demo.controller.ChapterController.*(..))")
    public void log(){
    }

    /**
     * @MethodName: doBefore
     * @Description: joinPoint用于获取域切入点方法有关的信息
     * @Author: Peng Lu
     * @Data: 2020/7/19 14:23
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request=requestAttributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getLocalAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName());
        //方法参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("doAfter={}",2222222);
    }

    /**
     * @MethodName: doAfterReturning
     * @Description: 得到response返回的数据，returning代表切入点方法返回的数据
     * @Author: Peng Lu
     * @Data: 2020/7/19 14:23
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }

}
