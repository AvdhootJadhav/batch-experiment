package com.avdhoot.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@EnableScheduling
public class JobConfig {

    @Bean
    public CommentReader commentReader() {
        return new CommentReader();
    }

    @Bean
    public CommentProcessor commentProcessor() {
        return new CommentProcessor();
    }

    @Bean
    public CommentWriter commentWriter() {
        return new CommentWriter();
    }

    @Bean
    public Step jobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("jobStep", jobRepository)
                .<Comment, CommentData>chunk(10,transactionManager)
                .reader(commentReader())
                .processor(commentProcessor())
                .writer(commentWriter())
                .build();
    }

    @Bean
    public Job firstJob(JobRepository jobRepository, Step jobStep) {
        return new JobBuilder("firstJob", jobRepository)
                .start(jobStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
