package com.jobboard.dao;

import com.jobboard.model.Job;

import java.util.List;

public interface JobDao {
    Job getJobById(int jobId);
    List<Job> getAllJobs();
    List<Job> getJobsByClientId(int clientId);
    List<Job> getJobsByCategoryId(int categoryId);
    Job createJob(Job job);
    void updateJob(Job job);
    void deleteJob(int jobId);

}

