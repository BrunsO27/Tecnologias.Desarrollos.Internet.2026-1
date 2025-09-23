/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brunoortiz
 */
public class BookDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/book_store?useSSL=false&serverTimezone=UTC";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "OIAB020127";
    
    private static final String SELECT_ALL = "SELECT * FROM books";
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
    
    // Conexion
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch(ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
    
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        
        try(Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                books.add(new Book(title, author, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    public void addBook(Book book) throws SQLException {
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_BOOK)) {
            
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Book> searchBooks(String keyword) throws SQLException {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            double price = rs.getDouble("price");
            books.add(new Book(title, author, price));
        }
    }
    return books;
}
}
