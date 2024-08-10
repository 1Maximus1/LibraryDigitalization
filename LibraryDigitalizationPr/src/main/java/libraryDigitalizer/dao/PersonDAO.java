package libraryDigitalizer.dao;

import libraryDigitalizer.model.Person;
import libraryDigitalizer.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM People", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        Optional<Person> person = jdbcTemplate.query("SELECT * FROM People WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

        if (person.isPresent()){
            return person.get();
        }else {
            throw new PersonNotFoundException(String.format("Person with id = %d was not found", id));
        }
    }

    public Optional<Person> show(String name){
        return jdbcTemplate.query("SELECT * FROM People WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM People where person_id=?", id);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE People set name=?, year=? where person_id=?",updatedPerson.getName(), updatedPerson.getYear(), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO People(name,year) values (?,?)",person.getName(),person.getYear());
    }
}
