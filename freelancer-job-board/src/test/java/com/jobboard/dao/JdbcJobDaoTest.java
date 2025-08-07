package com.jobboard.dao;

import com.jobboard.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JdbcJobDaoTest extends BaseDaoTest {

    private JdbcJobDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcJobDao(jdbcTemplate);
    }


    @Test
    public void getJobById_existingId_shouldReturnJob() {
        Job job = dao.getJobById(1);
        assertNotNull(job);
        assertEquals("Build a Portfolio Website", job.getTitle());
    }


    @Test
    public void getJobById_invalidId_shouldReturnNull() {
        Job job = dao.getJobById(999);
        assertNull(job);
    }


    @Test
    public void getAllJobs_shouldReturnAtLeastTwo() {
        List<Job> jobs = dao.getAllJobs();
        assertNotNull(jobs);
        assertTrue(jobs.size() >= 2);
    }


    @Test
    public void getJobsByClientId_valid_shouldReturnJobs() {
        List<Job> jobs = dao.getJobsByClientId(1);
        assertNotNull(jobs);
        assertEquals(2, jobs.size());
    }


    @Test
    public void getJobsByClientId_invalid_shouldReturnEmptyList() {
        List<Job> jobs = dao.getJobsByClientId(999);
        assertNotNull(jobs);
        assertTrue(jobs.isEmpty());
    }


    @Test
    public void createJob_valid_shouldSucceed() {
        Job newJob = new Job();
        newJob.setTitle("New Test Job");
        newJob.setDescription("This is a test job");
        newJob.setPosted_by(1);       // client cali
        newJob.setCategory_id(1);     // Web Development

        Job created = dao.createJob(newJob);

        assertNotNull(created);
        assertTrue(created.getJob_id() > 0);

        Job fetched = dao.getJobById(created.getJob_id());
        assertEquals("New Test Job", fetched.getTitle());
    }


    @Test
    public void createJob_invalidCategory_shouldThrow() {
        Job job = new Job();
        job.setTitle("Invalid");
        job.setDescription("bad category");
        job.setPosted_by(1);
        job.setCategory_id(999); // doesn't exist

        assertThrows(DataIntegrityViolationException.class, () -> dao.createJob(job));
    }


}
