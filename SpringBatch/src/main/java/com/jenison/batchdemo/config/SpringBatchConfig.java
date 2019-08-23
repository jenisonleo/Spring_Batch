package com.jenison.batchdemo.config;

import com.jenison.batchdemo.model.Users;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    @Bean
    public Job createJob(JobBuilderFactory jobBuilderFactory,
                         StepBuilderFactory stepBuilderFactory,
                         ItemReader<Users> itemReader,
                         ItemProcessor<Users,Users> itemProcessor,
                         ItemWriter<Users> itemWriter
                         ){
        Step step=stepBuilderFactory.get("ETL-step-builder")
                .<Users,Users>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        return jobBuilderFactory.get("ETL-job-builder")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<Users> constructItemReader() throws Exception{
        Resource resource=new ClassPathResource("users.csv");
        System.out.println("jenison lenght "+resource.contentLength());
        FlatFileItemReader<Users> flatFileItemReader=new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("input reader jenison");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());

        return flatFileItemReader;
    }

    private LineMapper<Users> lineMapper() {
        DefaultLineMapper<Users> lineMapper=new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setNames(new String[]{"id","name","dept","salary"});
        delimitedLineTokenizer.setStrict(false);

        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<Users> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Users.class);
        lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        return lineMapper;
    }
}
