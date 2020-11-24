package com.yuan.springbatch.batch;

import com.yuan.springbatch.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;



/**
 * @Author yuanxiya
 * @Description 处理数据
 * @Date 2020/11/24 23:31
 */
public class PersonItemProcess implements ItemProcessor<Person,String> {

    private static final Logger logger = LoggerFactory.getLogger(PersonItemProcess.class);

    @Override
    public String process(Person person) throws Exception {
        logger.info("姓:{}"+person.getFirstName()+"名:{}"+person.getLastName());
        return person.getFirstName()+person.getLastName()+"greeting!!!!!";
    }
}
