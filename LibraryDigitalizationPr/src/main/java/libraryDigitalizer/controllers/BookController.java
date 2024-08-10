package libraryDigitalizer.controllers;

import jakarta.validation.Valid;
import libraryDigitalizer.dao.BookDAO;
import libraryDigitalizer.dao.PersonDAO;
import libraryDigitalizer.model.Book;
import libraryDigitalizer.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("personSearch", bookDAO.searchOfOwner(id));
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.create(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }

        bookDAO.update(id, book);
        return "redirect:/books";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";

    }

    @PatchMapping("/add/{id}")
    public String makeOwner(@PathVariable("id") int id, Person person){
        bookDAO.appointing(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/free/{id}")
    public String releaseOwner(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }


}
