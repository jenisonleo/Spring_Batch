package com.jenison.batchdemo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    JobLauncher jobLauncher;


    @Autowired
    Job job;

    @RequestMapping(path = "/begin",method = RequestMethod.GET)
    public String beginProcess() throws Exception{
        JobParameters jobParameters=new JobParameters();
        jobLauncher.run(job,jobParameters);
        return "launchSuccess";
    }
}
