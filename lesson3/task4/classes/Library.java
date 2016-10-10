package task4.classes;

import task4.interfaces.Book;
import task4.interfaces.Reader;

import java.util.Arrays;

/**
 * Created by prokop on 9.10.16.
 */
public class Library {
    private Book[] books;
    private Reader[] readers;

    public Library() {

    }

    public Library(Book[] books, Reader[] readers) {
        this.books = books;
        this.readers = readers;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Reader[] getReaders() {
        return readers;
    }

    public void setReaders(Reader[] readers) {
        this.readers = readers;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + Arrays.toString(books) +
                ", readers=" + Arrays.toString(readers) +
                '}';
    }
}
