package com.yuan.springbatch;

import com.yuan.springbatch.batch.BatchConfig;
import com.yuan.springbatch.batch.HelloWorldJobConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * @Author yuanxiya
 * @Description TODO
 * @Date 2020/11/24 23:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBatchApplicationTest.JobTestInnerConfigClass.class})
public class SpringBatchApplicationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void jobTest() throws Exception {
        JobExecution execution = jobLauncherTestUtils.launchJob();
        assertEquals("COMPLETED",execution.getExitStatus().getExitCode());


    }




    @Configuration
    @Import({BatchConfig.class, HelloWorldJobConfig.class})
    static class JobTestInnerConfigClass{
        @Autowired
        private Job job;

        @Bean
        public JobLauncherTestUtils getJobLauncherTestUtils() throws NoSuchJobException {
            JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
            jobLauncherTestUtils.setJob(job);
            return jobLauncherTestUtils;
        }
    }





}
