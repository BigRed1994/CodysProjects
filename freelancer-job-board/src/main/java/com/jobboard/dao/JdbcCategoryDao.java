package com.jobboard.dao;

import com.jobboard.model.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JdbcCategoryDao implements CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM category WHERE category_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryRowMapper(), categoryId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT category_id, name FROM category";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Category category = new Category();
            category.setCategory_id(results.getInt("category_id"));
            category.setName(results.getString("name"));
            categories.add(category);
        }

        return categories;
    }


    @Override
    public Category createCategory(Category category) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            return ps;
        }, keyHolder);


        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("category_id")) {
            category.setCategory_id(((Number) keys.get("category_id")).intValue());
        }

        return category;
    }


    @Override
    public void updateCategory(Category category) {
        String sql = "UPDATE category SET name = ? WHERE category_id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getCategory_id());
    }

    @Override
    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM category WHERE category_id = ?";
        jdbcTemplate.update(sql, categoryId);
    }

    private static class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategory_id(rs.getInt("category_id"));
            category.setName(rs.getString("name"));
            return category;
        }
    }
}

