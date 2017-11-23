package ohtu;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.*;
import ohtu.Dao.BookDao;
import java.util.HashMap;
import ohtu.database.Database;
import ohtu.domain.Book;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:tietokanta.db");
        BookDao books = new BookDao(database);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/books", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("books", books.findAll());
            return new ModelAndView(map, "books");
        }, new ThymeleafTemplateEngine());

        Spark.get("/books/:id", (req, res) -> {
            System.out.println("eterwerwa");
            HashMap map = new HashMap<>();
            Integer bookId = Integer.parseInt(req.params(":id"));
            System.out.println("nyt etitää yks");
            map.put("book", books.findOne(bookId));
            return new ModelAndView(map, "book");
        }, new ThymeleafTemplateEngine());

        Spark.post("/books", (request, response) -> {
            String author = request.queryParams("author");
            String title = request.queryParams("title");
            String isbn = request.queryParams("ISBN");
            String tags = request.queryParams("tags");
            Book book = new Book(title, author, isbn, tags);
            books.saveOrUpdate(book);
            response.redirect("/books");
            return "";
        });
    }
}
