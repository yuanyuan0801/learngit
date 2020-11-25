package com.batch.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.DefaultExceptionHandler;
import org.springframework.stereotype.Component;

/**
 * @Author yuanxiya
 * @Description 异常处理类
 * @Date 2020/11/25 23:20
 */
@Component
public class StepExceptionHandler extends DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(StepExceptionHandler.class);

    @Override
    public void handleException(RepeatContext context, Throwable throwable) throws Throwable {
        //super.handleException(context, throwable);
        logger.error("step出错："+throwable.getMessage());
        throw new JobInterruptedException("step出错："+throwable.getMessage());
    }
}
