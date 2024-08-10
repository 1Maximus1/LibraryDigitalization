package libraryDigitalizer.util;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
