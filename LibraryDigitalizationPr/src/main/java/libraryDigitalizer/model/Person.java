package libraryDigitalizer.model;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: Surname Name")
    private String name;


    @Min(value = 1900, message="Year should be bigger than 1900")
    private int year;

    public Person(){}

    public Person( String name, int year) {
        this.name = name;
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
