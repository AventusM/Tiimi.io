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
            Book b = new Book(result.getInt("id"), result.getString("title"), result.getString("author"), result.getString("ISBN"), result.getString("tags"));
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
                users.add(new Book(result.getInt("id"), result.getString("title"), result.getString("author"), result.getString("ISBN"), result.getString("tags")));
            }
        }

        return users;
    }

    @Override
    public Book save(Book book) throws SQLException {
        Book byName = findByName(book.getTitle());

        if (byName != null) {
            return findByName(byName.getTitle());
        } //ei haluta kahta saman nimistä

        //Tallennetaan kirja
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Book (title, author, ISBN, tags, dateAdded) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getISBN());
            stmt.setString(4, book.getTags());
            stmt.setDate(5, book.getTime());
            stmt.executeUpdate();
        }

        return findByName(book.getTitle());

    }

    @Override
    public Book update(Book book) throws SQLException {
//        Book book = findOne(id);
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE Book"
                + " SET title = ?, author = ?, ISBN = ?, tags = ?"
                + " WHERE id = ?");
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getISBN());
        statement.setString(4, book.getTags());
        statement.setInt(5, book.getId());
        statement.executeUpdate();

        return findByName(book.getTitle());
    }

    public Book findByName(String title) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book WHERE title = ?");
            stmt.setString(1, title);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Book(result.getInt("id"), result.getString("title"), result.getString("author"), result.getString("ISBN"), result.getString("tags"));
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Book WHERE id = ?");
        statement.setInt(1, key);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

}
