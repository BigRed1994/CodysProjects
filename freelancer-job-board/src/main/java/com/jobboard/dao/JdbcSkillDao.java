package com.jobboard.dao;

import com.jobboard.model.Skill;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class JdbcSkillDao implements SkillDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSkillDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Skill getSkillById(int skillId) {
        String sql = "SELECT * FROM skill WHERE skill_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new SkillRowMapper(), skillId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Skill> getAllSkills() {
        String sql = "SELECT * FROM skill";
        return jdbcTemplate.query(sql, new SkillRowMapper());
    }

    @Override
    public Skill createSkill(Skill skill) {
        String sql = "INSERT INTO skill (name) VALUES (?) RETURNING skill_id";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class, skill.getName());
        skill.setSkillId(newId);
        return skill;
    }

    @Override
    public void updateSkill(Skill skill) {
        String sql = "UPDATE skill SET name = ? WHERE skill_id = ?";
        jdbcTemplate.update(sql, skill.getName(), skill.getSkillId());
    }

    @Override
    public void deleteSkill(int skillId) {
        String sql = "DELETE FROM skill WHERE skill_id = ?";
        jdbcTemplate.update(sql, skillId);
    }

    private static class SkillRowMapper implements RowMapper<Skill> {
        @Override
        public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Skill skill = new Skill();
            skill.setSkillId(rs.getInt("skill_id"));
            skill.setName(rs.getString("name"));
            return skill;
        }
    }
}

