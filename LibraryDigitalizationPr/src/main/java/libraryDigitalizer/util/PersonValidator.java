package libraryDigitalizer.util;

import libraryDigitalizer.dao.PersonDAO;
import libraryDigitalizer.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personDAO.show(person.getName()).isPresent()){
            errors.rejectValue("name", "", "This name has already taken!");
        }
    }
}
