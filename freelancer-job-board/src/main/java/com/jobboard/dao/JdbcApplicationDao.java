package com.jobboard.dao;

import com.jobboard.model.Application;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcApplicationDao implements ApplicationDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Application getApplicationById(int id) {
        String sql = "SELECT * FROM application WHERE application_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ApplicationRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Application> getAllApplications() {
        String sql = "SELECT * FROM application";
        return jdbcTemplate.query(sql, new ApplicationRowMapper());
    }

    @Override
    public List<Application> getApplicationsByFreelancerId(int freelancerId) {
        String sql = "SELECT * FROM application WHERE freelancer_id = ?";
        return jdbcTemplate.query(sql, new ApplicationRowMapper(), freelancerId);
    }

    @Override
    public List<Application> getApplicationsByJobId(int jobId) {
        String sql = "SELECT * FROM application WHERE job_id = ?";
        return jdbcTemplate.query(sql, new ApplicationRowMapper(), jobId);
    }

    @Override
    public Application createApplication(Application application) {
        String sql = "INSERT INTO application (freelancer_id, job_id, cover_letter, status) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, application.getFreelancer_id());
            ps.setInt(2, application.getJob_id());
            ps.setString(3, application.getCover_letter());
            ps.setString(4, application.getStatus());
            return ps;
        }, keyHolder);

        int newId = (int) keyHolder.getKeys().get("application_id");
        application.setApplication_id(newId);
        return application;
    }

    @Override
    public void updateApplication(Application application) {
        String sql = "UPDATE application SET freelancer_id = ?, job_id = ?, cover_letter = ?, status = ? " +
                "WHERE application_id = ?";
        jdbcTemplate.update(sql,
                application.getFreelancer_id(),
                application.getJob_id(),
                application.getCover_letter(),
                application.getStatus(),
                application.getApplication_id());
    }

    @Override
    public void deleteApplication(int applicationId) {
        String sql = "DELETE FROM application WHERE application_id = ?";
        jdbcTemplate.update(sql, applicationId);
    }


    private static class ApplicationRowMapper implements RowMapper<Application> {
        @Override
        public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
            Application app = new Application();
            app.setApplication_id(rs.getInt("application_id"));
            app.setFreelancer_id(rs.getInt("freelancer_id"));
            app.setJob_id(rs.getInt("job_id"));
            app.setCover_letter(rs.getString("cover_letter"));
            app.setStatus(rs.getString("status"));
            return app;
        }
    }
}

