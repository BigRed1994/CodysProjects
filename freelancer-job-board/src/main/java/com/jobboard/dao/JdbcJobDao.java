package com.jobboard.dao;

import com.jobboard.model.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcJobDao implements JobDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcJobDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Job createJob(Job job) {
        String sql = "INSERT INTO job (title, description, posted_by, category_id) " +
                "VALUES (?, ?, ?, ?) RETURNING job_id";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class,
                job.getTitle(), job.getDescription(), job.getPosted_by(), job.getCategory_id());

        job.setJob_id(newId);

        job.setJob_id(newId);
        return job;
    }

    @Override
    public void updateJob(Job job) {

    }

    @Override
    public void deleteJob(int jobId) {

    }

    @Override
    public Job getJobById(int jobId) {
        String sql = "SELECT * FROM job WHERE job_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, jobId);

        if (result.next()) {
            return mapRowToJob(result);
        } else {
            return null;
        }
    }


    @Override
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            jobs.add(mapRowToJob(results));
        }
        return jobs;
    }

    @Override
    public List<Job> getJobsByClientId(int clientId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE posted_by = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, clientId);
        while (results.next()) {
            jobs.add(mapRowToJob(results));
        }
        return jobs;
    }

    @Override
    public List<Job> getJobsByCategoryId(int categoryId) {
        return null;
    }

    private Job mapRowToJob(SqlRowSet row) {
        Job job = new Job();
        job.setJob_id(row.getInt("job_id"));
        job.setTitle(row.getString("title"));
        job.setDescription(row.getString("description"));
        job.setPosted_by(row.getInt("posted_by"));
        job.setCategory_id(row.getInt("category_id"));
        return job;
    }
}
