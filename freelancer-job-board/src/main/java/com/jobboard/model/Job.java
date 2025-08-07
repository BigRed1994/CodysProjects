package com.jobboard.model;

public class Job {
    private  int jobs_id;
    private String title;

    private String description;

    private int posted_by;
    private int category_id;

    private  int client_id;

    public Job(){

    }
    public Job(int jobs_id, String title, String description, int posted_by, int category_id){
        this.jobs_id = jobs_id;
        this.title = title;
        this.description = description;
        this.posted_by = posted_by;
        this.category_id = category_id;
    }

    public int getJob_id() {
        return jobs_id;
    }

    public void setJob_id(int jobs_id) {
        this.jobs_id = jobs_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(int posted_by) {
        this.posted_by = posted_by;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return title + " (Posted by: " + posted_by + ")";
    }

    public void setClient_id(int userId) {
    }

    public int getClient_id() {
        return client_id;
    }



}
