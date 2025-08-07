package com.jobboard.dao;

import com.jobboard.dao.JdbcApplicationDao;
import com.jobboard.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JdbcApplicationDaoTest extends BaseDaoTest {

    private JdbcApplicationDao dao;
    JdbcTemplate jdbcTemplate;

    private int testUserId = 1;
    private int testCategoryId;
    private int testJobId = 1;

    @BeforeEach
    public void setupTestData() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcApplicationDao(jdbcTemplate);
    }


    @Test
    public void testCreateAndGetApplicationById() {
        Application app = new Application();
        app.setFreelancer_id(testUserId);
        app.setJob_id(testJobId);
        app.setCover_letter("Excited to apply!");
        app.setStatus("pending");

        Application created = dao.createApplication(app);
        assertTrue(created.getApplication_id() > 0);

        Application fetched = dao.getApplicationById(created.getApplication_id());
        assertNotNull(fetched);
        assertEquals("Excited to apply!", fetched.getCover_letter());
        assertEquals("pending", fetched.getStatus());
    }

    @Test
    public void testGetAllApplications() {
        int beforeSize = dao.getAllApplications().size();

        Application app = new Application();
        app.setFreelancer_id(testUserId);
        app.setJob_id(testJobId);
        app.setCover_letter("Another one!");
        app.setStatus("pending");
        dao.createApplication(app);

        int afterSize = dao.getAllApplications().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void testGetApplicationsByFreelancerId() {
        Application app = new Application();
        app.setFreelancer_id(testUserId);
        app.setJob_id(testJobId);
        app.setCover_letter("From test user");
        app.setStatus("submitted");
        dao.createApplication(app);

        List<Application> apps = dao.getApplicationsByFreelancerId(testUserId);
        assertFalse(apps.isEmpty());
        for (Application a : apps) {
            assertEquals(testUserId, a.getFreelancer_id());
        }
    }

    @Test
    public void testGetApplicationsByJobId() {
        Application app = new Application();
        app.setFreelancer_id(testUserId);
        app.setJob_id(testJobId);
        app.setCover_letter("For test job");
        app.setStatus("applied");
        dao.createApplication(app);

        List<Application> apps = dao.getApplicationsByJobId(testJobId);
        assertFalse(apps.isEmpty());
        for (Application a : apps) {
            assertEquals(testJobId, a.getJob_id());
        }
    }

    @Test
    public void testUpdateApplication() {
        Application app = new Application();
        app.setFreelancer_id(testUserId);
        app.setJob_id(testJobId);
        app.setCover_letter("Initial letter");
        app.setStatus("pending");

        Application created = dao.createApplication(app);
        created.setCover_letter("Updated letter");
        created.setStatus("reviewed");

        dao.updateApplication(created);
        Application updated = dao.getApplicationById(created.getApplication_id());

        assertEquals("Updated letter", updated.getCover_letter());
        assertEquals("reviewed", updated.getStatus());
    }

    @Test
    public void testDeleteApplication() {
        Application app = new Application();
        app.setFreelancer_id(testUserId);
        app.setJob_id(testJobId);
        app.setCover_letter("Delete me");
        app.setStatus("pending");

        Application created = dao.createApplication(app);
        int id = created.getApplication_id();

        dao.deleteApplication(id);
        Application deleted = dao.getApplicationById(id);
        assertNull(deleted);
    }
}
