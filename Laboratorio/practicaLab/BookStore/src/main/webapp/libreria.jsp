<%-- 
    Document   : libreria
    Created on : 21 sep 2025, 8:17:20 a.m.
    Author     : brunoortiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Libreria </title>
    </head>
    <body>
     
     <h1> Libreria </h1>

    <h2>Registrar un nuevo libro</h2>
    <form action="books" method="post">
        <label for="title">Título:</label>
        <input type="text" name="title" required>

        <label for="author">Autor:</label>
        <input type="text" name="author" required>

        <label for="price">Precio:</label>
        <input type="number" name="price" step="0.01" required>

        <button type="submit">Registrar</button>
    </form>
    
    <h2> Buscar libros </h2>
    <form action="books" method="get">
        <input type="text" name="search" placeholder="Buscar por título o autor">
        <button type="submit">Buscar</button>
    </form>

    <h2>Lista de libros disponibles</h2>
    
    <table>
        <thead>
            <tr>
                <th>Título</th>
                <th>Autor</th>
                <th>Precio</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </body>
</html>
