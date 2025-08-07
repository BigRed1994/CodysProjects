package com.jobboard.controllers;

import com.jobboard.dao.ApplicationDao;
import com.jobboard.dao.UserDao;
import com.jobboard.model.Application;
import com.jobboard.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationDao applicationDao;
    private final UserDao userDao;

    public ApplicationController(ApplicationDao applicationDao, UserDao userDao) {
        this.applicationDao = applicationDao;
        this.userDao = userDao;
    }
    @PreAuthorize("hasRole('CLIENT')") 
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationDao.getAllApplications();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(@PathVariable int jobId) {
        return applicationDao.getApplicationsByJobId(jobId);
    }

    @PostMapping
    @PreAuthorize("hasRole('FREELANCER')")
    public Application createApplication(@RequestBody Application application, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        application.setFreelancer_id(user.getId());
        return applicationDao.createApplication(application);

    }



}

