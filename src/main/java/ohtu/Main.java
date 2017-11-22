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
            return new ModelAndView(map, "index.html");
        }, new ThymeleafTemplateEngine());

        Spark.get("/books", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("books", books.findAll());
            return new ModelAndView(map, "books");
        }, new ThymeleafTemplateEngine());

        Spark.get("/books/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Integer bookId = Integer.parseInt(req.params(":id"));
            try {
                map.put("book", books.findOne(bookId));
            } catch (SQLException ex) {
                System.out.println("id not found");
            }
            return new ModelAndView(map, "book");
        }, new ThymeleafTemplateEngine());
    }
}
