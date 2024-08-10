package libraryDigitalizer.dao;

import libraryDigitalizer.model.Book;
import libraryDigitalizer.model.Person;
import libraryDigitalizer.util.BookNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM BOOKS",new BeanPropertyRowMapper<>(Book.class));
    }


    public void create(Book book) {
        jdbcTemplate.update("INSERT into Books(person_id, title, author, yearOfProduction) values (?,?,?,?)",null, book.getTitle(), book.getAuthor(), book.getYearOfProduction());
    }

    public Book show(int id){
        Optional<Book> optionalBook = jdbcTemplate.query("SElECT * FROM BOOKS WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
        if (optionalBook.isPresent()){
            return optionalBook.get();
        }else {
            throw new BookNotFoundException(String.format("Book with id = %d was not found", id));
        }
    }

    public void update(int book_id, Book updatedBook) {
        jdbcTemplate.update("update Books set title=?, author=?, yearOfProduction=? where book_id=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfProduction(), book_id);
    }

    public List<Book> getUserBooks(int id){
        return jdbcTemplate.query("SELECT * FROM Books where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Books WHERE book_id=?", id);
    }

    public void appointing(int book_id, Person appointPerson){
        jdbcTemplate.update("UPDATE Books set person_id=? where book_id=?", appointPerson.getPerson_id(),book_id);
    }

    public Person searchOfOwner(int book_id) {
        return jdbcTemplate.query("select people.person_id, name,year,title from people join books on people.person_id = books.person_id and book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void release(int book_id) {
        jdbcTemplate.update("UPDATE Books set person_id=null where book_id=?", book_id);
    }
}
