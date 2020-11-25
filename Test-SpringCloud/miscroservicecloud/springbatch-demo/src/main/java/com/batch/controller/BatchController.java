package com.batch.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时启动批处理任务
 */
@Component
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Resource(name = "batchInsertJob")
    private Job job;

    //@Scheduled(cron = "0 0 8 * * ?") // 早上8点
    // 每分钟跑一次
    @Scheduled(cron = "0 0/1 * * * ?")
    public void job() throws Exception{
        JobExecution jobExecution = jobLauncher.run(job,new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters());
        jobExecution.getId();
    }
}
