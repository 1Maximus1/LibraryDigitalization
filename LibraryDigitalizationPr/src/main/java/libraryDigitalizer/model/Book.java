package libraryDigitalizer.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Book {

    private int book_id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Author should be specified")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author's name should be in this format: Surname Name")
    private String author;

    @Min(value = 1100, message="Year of production should be positive and bigger than 1100")
    private int yearOfProduction;

    public Book(){
    }

    public Book(String title, String author, int yearOfProduction){
        this.title=title;
        this.author=author;
        this.yearOfProduction = yearOfProduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
