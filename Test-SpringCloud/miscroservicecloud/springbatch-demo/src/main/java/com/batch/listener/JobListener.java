package com.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author yuanxiya
 * @Description 可以对每次批量任务进行统计
 * @Date 2020/11/25 23:14
 */
@Component
public class JobListener  implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(JobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info(LocalDateTime.now()+"开始job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
         logger.info(LocalDateTime.now() +"结束job");
    }
}
