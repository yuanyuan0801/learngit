package com.yuan.springbatch.batch;

import com.yuan.springbatch.batch.PersonItemProcess;
import com.yuan.springbatch.entity.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * @Author yuanxiya
 * @Description 配置 Spring Batch Job
 * @Date 2020/11/24 23:11
 */
@Configuration
public class HelloWorldJobConfig {

    @Bean
    public Job helloWorldJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory){
            return  jobBuilderFactory.get("helloWorldJob")
                                     .start(helloWorldStep(stepBuilderFactory))
                                     .build();
    }

    @Bean
    public Step helloWorldStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("helloWorldStep")
                                  .<Person,String>chunk(10)
                                  .reader(read())
                                  .processor(processor())
                                  .writer(writer()).build();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
          return new FlatFileItemWriterBuilder<String>().name("personItemWriter")
                                                         .resource(new FileSystemResource("target/greeting.txt"))
                                                         .lineAggregator(new PassThroughLineAggregator<>()).build();
    }

    @Bean
    public PersonItemProcess processor() {
        return new PersonItemProcess();
    }

    @Bean
    public FlatFileItemReader<Person> read() {
        return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
                                                       .resource(new ClassPathResource("file/person.csv"))
                                                       .delimited()
                                                       .names(new String[]{"firstName","lastName"})
                                                       .targetType(Person.class).build();
    }
}
