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

/**
 *
 * @author tminka/ Only to test, this class will probably change
 */
public class BookDao implements Dao<Book, Integer> {

    private Database database;

    public BookDao(Database database) {
        this.database = database;
    }

    @Override
    public Book findOne(Integer key) throws SQLException {
        return findAll().stream().filter(u -> u.getId().equals(key)).findFirst().get();
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> users = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, name FROM Book").executeQuery()) {

            while (result.next()) {
                users.add(new Book(result.getInt("id"), result.getString("name")));
            }
        }

        return users;
    }

    @Override
    public Book saveOrUpdate(Book object) throws SQLException {
        Book byName = findByName(object.getName());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Book (name) VALUES (?)");
            stmt.setString(1, object.getName());
            stmt.executeUpdate();
        }

        return findByName(object.getName());

    }

    private Book findByName(String name) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM Book WHERE name = ?");
            stmt.setString(1, name);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Book(result.getInt("id"), result.getString("name"));
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
