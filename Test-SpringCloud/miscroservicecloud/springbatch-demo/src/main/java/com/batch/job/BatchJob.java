package com.batch.job;

import com.batch.exception.StepExceptionHandler;
import com.batch.listener.JobListener;
import com.batch.model.Student;
import com.batch.model.StudentDto;
import com.batch.processer.BatchItemProcesser;
import com.batch.writer.BatchItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.validation.BindException;

import java.io.File;

@Configuration
public class BatchJob {


    private static final Logger logger = LoggerFactory.getLogger(BatchJob.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private BatchItemProcesser batchItemProcesser;
    @Autowired
    private BatchItemWriter batchItemWriter;
    @Autowired
    private JobListener jobListener;
    @Autowired
    private StepExceptionHandler stepExceptionHandler;




    @Bean("batchInsertJob")
    public Job batchInsertJob(){
        return jobBuilderFactory.get("batchInsertJob")
                                .start(batchInsertStep())
                               .listener(jobListener)
                                .build();

    }


    @Bean
    public Step batchInsertStep() {
        logger.info("batchInsertStep()");
          return stepBuilderFactory.get("batchInsertStep")
                                   .<Student, StudentDto>chunk(10)
                                   .reader(fielReader())
                                   .processor(batchItemProcesser)
                                   .writer(batchItemWriter)
                                   .faultTolerant()   //容错
                                   .skipLimit(10) //诉Spring最多允许跳过多少个items,超过则job失败
                                   .skip(Exception.class) //可接受的异常类型
                                   .taskExecutor(new SimpleAsyncTaskExecutor())//异步执行用户任务的SimpleAsyncTaskExecutor,可控制并发上限、资源节流
                                   .allowStartIfComplete(true).startLimit(10) //该步骤可以运行的次数(最大重跑次数)
                                   .exceptionHandler(stepExceptionHandler) //设置并发方式执行exceptionHandler,异常时打印日志并抛出异常
                                   .throttleLimit(10)//// 并发任务数为 10,默认为4
                                   .build();




    }




    public FlatFileItemReader<Student> fielReader() {
        logger.info("读取数据开始");

        return new FlatFileItemReaderBuilder<Student>().name("studentItemReader")
                .resource(new ClassPathResource("file/student.csv"))
                .delimited()
                .names(new String[]{"id","name","age"})
                .targetType(Student.class).build();


//        FlatFileItemReader<Student> fileRead = new FlatFileItemReader<>();
//        fileRead.setEncoding("UTF-8");
//        fileRead.setResource(new FileSystemResource(new File("D:\\student.txt")));
//        //fileRead.setLinesToSkip(2);跳过开头多少行
//        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<Student>();
//        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(","));
//        lineMapper.setFieldSetMapper(new FieldSetMapper<Student>() {
//
//            @Override
//            public Student mapFieldSet(FieldSet fieldSet) throws BindException {
//                Student student = new Student();
//                try {
//                    student.setId(fieldSet.readInt(0));
//                    student.setName(fieldSet.readString(1));
//                    student.setAge(fieldSet.readInt(2));
//
//                } catch (Exception e) {
//                    logger.error("解析异常："+e.getMessage());
//                }
//                return student;
//            }
//        });
//        fileRead.setLineMapper(lineMapper);
//        return fileRead;


    }


}
