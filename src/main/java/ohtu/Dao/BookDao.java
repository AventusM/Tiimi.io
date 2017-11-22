/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtu.database.Database;
import ohtu.domain.Book;

public class BookDao implements Dao<Book, Integer> {

    private Database database;

    public BookDao(Database database) {
        this.database = database;
    }

    @Override
    public Book findOne(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book WHERE id = ?");
            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }
            Book b = new Book(result.getInt("id"), result.getString("title"), result.getString("author"), result.getString("ISBN"), result.getString("tags"), result.getDate("dateAdded"));
            System.out.println(b.getTitle() + " " + b.getTags());
            return b;

        }
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> users = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM Book").executeQuery()) {

            while (result.next()) {
                users.add(new Book(result.getInt("id"), result.getString("title"), result.getString("author"), result.getString("ISBN"), result.getString("tags"), result.getDate("dateAdded")));
            }
        }

        return users;
    }

    @Override
    public Book saveOrUpdate(Book object) throws SQLException {
        Book byName = findByName(object.getTitle());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Book (title, author, ISBN, tags, dateAdded) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, object.getTitle());
            stmt.setString(2, object.getAuthor());
            stmt.setString(3, object.getISBN());
            stmt.setString(4, object.getTags());
            stmt.setDate(5, object.getTime());
            stmt.executeUpdate();
        }

        return findByName(object.getTitle());

    }

    private Book findByName(String title) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book WHERE title = ?");
            stmt.setString(1, title);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Book(result.getInt("id"), result.getString("title"), result.getString("author"), result.getString("ISBN"), result.getString("tags"), result.getDate("dateAdded"));
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
