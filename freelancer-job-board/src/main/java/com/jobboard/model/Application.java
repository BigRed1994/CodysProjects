package com.jobboard.model;

public class Application {
    private int application_id;
    private int freelancer_id;
    private int job_id;
    private String cover_letter;
    private String status;

    public Application(){

    }
    public Application(int application_id, int freelancer_id, int job_id, String cover_letter, String status){

        this.application_id = application_id;
        this.freelancer_id = freelancer_id;
        this.job_id = job_id;
        this.cover_letter =cover_letter;
        this.status = status;
    }

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public int getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(int freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getCover_letter() {
        return cover_letter;
    }

    public void setCover_letter(String cover_letter) {
        this.cover_letter = cover_letter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application for Job ID " + job_id + " by Freelancer ID " + freelancer_id;
    }



}
