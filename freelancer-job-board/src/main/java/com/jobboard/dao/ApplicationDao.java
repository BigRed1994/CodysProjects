package com.jobboard.dao;

import com.jobboard.model.Application;

import java.util.List;

public interface ApplicationDao {

    Application getApplicationById(int id);

    List<Application> getAllApplications();

    List<Application> getApplicationsByFreelancerId(int freelancerId);

    List<Application> getApplicationsByJobId(int jobId);

    Application createApplication(Application application);

    void updateApplication(Application application);

    void deleteApplication(int applicationId);
}
