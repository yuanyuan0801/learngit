package com.yuan.springbatch.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author yuanxiya
 * @Description 为了使Spring Batch使用基于Map的JobRepository，我们需要扩展DefaultBatchConfigurer。
 *               重写setDataSource()方法以不设置DataSource，这将导致自动配置使用基于Map的JobRepository
 * @Date 2020/11/24 23:00
 */
@Configuration  //注解表明Spring可以使用该类作为bean定义的源
@EnableBatchProcessing //它支持所有所需Spring Batch特性。它还提供了设置批处理作业的基本配置
public class BatchConfig extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(DataSource dataSource) {
      //  super.setDataSource(dataSource);
    }
}
