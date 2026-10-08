package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    public void createTable() { jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS products(id BIGINT PRIMARY KEY, name VARCHAR(255), price DOUBLE)"); }
    public int save(Product product) { return jdbcTemplate.update("INSERT INTO products (id, name, price) VALUES (?, ?, ?)", product.getId(), product.getName(), product.getPrice()); }
    public List<Product> findAll() { return jdbcTemplate.query("SELECT * FROM products ORDER BY id", new ProductRowMapper()); }
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override public Product mapRow(ResultSet rs, int rowNum) throws SQLException { return new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price")); }
    }
}
