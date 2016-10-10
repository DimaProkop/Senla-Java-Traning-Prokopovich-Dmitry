package task4.interfaces;

import task4.exceptions.BookException;

/**
 * Created by prokop on 9.10.16.
 */
public interface Reader {

    Book[] getBooks();

    void setBooks(Book[] books);

    void addBook(Book book);

    void deleteBook(Book book) throws BookException;

    String getName();

    void setName(String name);
}
