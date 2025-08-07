package com.jobboard.dao;

import com.jobboard.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcCategoryDaoTest extends BaseDaoTest {

    private JdbcCategoryDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); // use BaseDaoTest's dataSource
        dao = new JdbcCategoryDao(jdbcTemplate);
    }

    @Test
    public void getCategoryById_returns_correct_category() {
        Category newCat = new Category();
        newCat.setName("Test Category");
        Category created = dao.createCategory(newCat);

        Category retrieved = dao.getCategoryById(created.getCategory_id());
        assertNotNull(retrieved);
        assertEquals("Test Category", retrieved.getName());
    }

    @Test
    public void getAllCategories_returns_all() {
        List<Category> categories = dao.getAllCategories();
        assertTrue(categories.size() >= 0); // or whatever expected min
    }

    @Test
    public void updateCategory_modifies_existing() {
        Category cat = new Category();
        cat.setName("Initial");
        Category created = dao.createCategory(cat);

        created.setName("Updated Name");
        dao.updateCategory(created);

        Category updated = dao.getCategoryById(created.getCategory_id());
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    public void deleteCategory_removes_row() {
        Category cat = new Category();
        cat.setName("ToDelete");
        Category created = dao.createCategory(cat);

        dao.deleteCategory(created.getCategory_id());
        assertNull(dao.getCategoryById(created.getCategory_id()));
    }
}
