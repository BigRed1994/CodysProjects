package com.jobboard.dao;

import com.jobboard.dao.JdbcSkillDao;
import com.jobboard.model.Skill;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JdbcSkillDaoTest extends BaseDaoTest {

    private JdbcSkillDao skillDao;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        jdbcTemplate = new JdbcTemplate(dataSource); // from BaseDaoTest
        skillDao = new JdbcSkillDao(jdbcTemplate);
    }

    @Test
    public void testCreateAndGetSkillById() {
        Skill skill = new Skill();
        skill.setName("Python");

        Skill created = skillDao.createSkill(skill);
        assertNotNull(created.getSkillId());

        Skill fetched = skillDao.getSkillById(created.getSkillId());
        assertEquals("Python", fetched.getName());
    }

    @Test
    public void testGetAllSkills() {
        skillDao.createSkill(new Skill(0, "JavaScript"));
        skillDao.createSkill(new Skill(0, "SQL"));

        List<Skill> skills = skillDao.getAllSkills();
        assertTrue(skills.size() >= 2);
    }

    @Test
    public void testUpdateSkill() {
        Skill skill = skillDao.createSkill(new Skill(0, "OldSkill"));
        skill.setName("UpdatedSkill");

        skillDao.updateSkill(skill);

        Skill updated = skillDao.getSkillById(skill.getSkillId());
        assertEquals("UpdatedSkill", updated.getName());
    }

    @Test
    public void testDeleteSkill() {
        Skill skill = skillDao.createSkill(new Skill(0, "TempSkill"));
        int skillId = skill.getSkillId();

        skillDao.deleteSkill(skillId);
        Skill deleted = skillDao.getSkillById(skillId);

        assertNull(deleted);
    }
}
