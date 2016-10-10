package task4.classes;

import task4.enums.BookStatus;
import task4.interfaces.Book;
import task4.interfaces.Reader;

/**
 * Created by prokop on 9.10.16.
 */
public class BookImpl implements Book{
    private String name;
    private BookStatus status;
    private Reader reader;

    public BookImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Reader getReader() {
        return reader;
    }

    @Override
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "BookImpl{" +
                "name='" + name + '\'' +
                ", reader=" + reader +
                '}';
    }
}
