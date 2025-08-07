package com.jobboard.controllers;

import com.jobboard.dao.JobDao;
import com.jobboard.model.Job;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobDao jobDao;

    public JobController(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobDao.getAllJobs();
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public Job createJob(@RequestBody Job job) {
        return jobDao.createJob(job);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteJob(@PathVariable int id) {
        jobDao.deleteJob(id);
    }
}
