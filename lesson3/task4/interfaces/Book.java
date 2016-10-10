package task4.interfaces;

import task4.enums.BookStatus;

/**
 * Created by prokop on 9.10.16.
 */
public interface Book {

    Reader getReader();

    void setReader(Reader reader);

    String getName();

    void setName(String name);

    BookStatus getStatus();

    void setStatus(BookStatus status);
}
