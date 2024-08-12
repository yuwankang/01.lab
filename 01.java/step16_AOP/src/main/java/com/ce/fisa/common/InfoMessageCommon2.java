package com.ce.fisa.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InfoMessageCommon2 {

    private static final Logger logger = LogManager.getLogger(InfoMessageCommon2.class);

    @Around("within(com.ce.fisa.biz.*)")
    public Object aroud(ProceedingJoinPoint point) {
        // 전처리 before
        logger.info("어서오세요 ~~~~~");
        Object bizReturnValue = null;
        try {
            /*
             * ProceedingJoinPoint proceed()
             * - biz 메소드의 전처리, 후처리, 리턴값 공통 처리, 예외 공통처리 다 가능
             * - 반환타입은 Object	
             * 	- biz 메소드는 반환 값이 있을수도 없을수도 있음
             *  - biz 메소드 반환값이 없을 경우  : null
             *  - "					있을 경우 : 반환값 반환 
             *  - * 주의사항 : biz 메소드 반환값이 존재할 경우 반드시 return 해야 함
             */
            bizReturnValue = point.proceed(); // 사용자에 의해 호출한 biz 메소드 실제 실행 메소드
            logger.info("공통2 " + bizReturnValue + " & 공통"); // return 후처리
        
        } catch (Throwable e) { // Object -> Throwable -> Exception
            logger.error("biz의 예외 발생시 공통처리 로직 *******", e); // 예외 처리
        }
        
        // non-return 후처리 after
        logger.info("~~~~~ 안녕히가세요");
        return bizReturnValue;
    }
}







