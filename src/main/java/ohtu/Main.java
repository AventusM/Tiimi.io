package ohtu;

//import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import spark.Spark;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Book> books = new ArrayList();
        Book DBSuperCH1 = new Book("Akira Toriyama", "Warriors of Universe 6", "978-4-08-880661-7");
        books.add(DBSuperCH1);

        Spark.get("/", (request, response) -> {
            HashMap map = new HashMap();
            ModelAndView MAV = new ModelAndView(map, "index");
            return MAV;
        }, new ThymeleafTemplateEngine());

        Spark.get("/browse", (request, response) -> {
            HashMap map = new HashMap();
            map.put("books", books);
            ModelAndView MAV = new ModelAndView(map, "browse");
            return MAV;
        }, new ThymeleafTemplateEngine());

        Spark.get("/add", (request, response) -> {
            HashMap map = new HashMap();
            ModelAndView MAV = new ModelAndView(map, "add");
            return MAV;
        }, new ThymeleafTemplateEngine());

        Spark.post("/add", (request, response) -> {
            String author = request.queryParams("author");
            String title = request.queryParams("title");
            String isbn = request.queryParams("isbn");
            books.add(new Book(author, title, isbn));
            response.redirect("/browse");
            return "";
        });

    }
}
