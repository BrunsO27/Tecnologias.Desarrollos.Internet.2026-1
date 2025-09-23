/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BookDao;
import Model.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brunoortiz
 */
@WebServlet("/books")
public class BookController extends HttpServlet {
    private BookDao bookDao;
    
    public void init() {
        bookDao = new BookDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Book> books;

        try {
            if (search != null && !search.trim().isEmpty()) {
                books = bookDao.searchBooks(search); // Buscar
            } else {
                books = bookDao.getAllBooks(); // Listar todos
            }   

            request.setAttribute("bookList", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("libreria.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al obtener los libros", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            Book newBook = new Book(title, author, price);
            bookDao.addBook(newBook);
        } catch (SQLException e) {
            throw new ServletException("Error al registrar el libro", e);
        }

        response.sendRedirect("books");
    }
    
}
