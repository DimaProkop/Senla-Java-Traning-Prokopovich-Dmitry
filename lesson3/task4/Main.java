package task4;

import task4.classes.BookImpl;
import task4.classes.Library;
import task4.classes.ReaderImpl;
import task4.dao.LibraryManager;
import task4.exceptions.BookException;
import task4.interfaces.Book;
import task4.interfaces.Reader;

/**
 * Created by prokop on 9.10.16.
 */
public class Main {
    public static void main(String[] args) throws BookException {
        Book[] books = new Book[]{
                new BookImpl("Java"),
                new BookImpl("C#"),
                new BookImpl("ReactJS"),
                new BookImpl("JS"),
                new BookImpl("C++"),
                new BookImpl("Fortran"),
                new BookImpl("Assembler"),
                new BookImpl("Linux"),
                new BookImpl("Spring"),
                new BookImpl("JS"),
                new BookImpl("C++"),
                new BookImpl("Fortran"),
                new BookImpl("Assembler"),
        };

        Reader[] readers = new Reader[]{
                new ReaderImpl("Dima"),
                new ReaderImpl("Slava"),
                new ReaderImpl("Sanya")
        };

        Library library = new Library();
        library.setBooks(books);
        library.setReaders(readers);
        LibraryManager libraryManager = new LibraryManager(library);

        try {
            libraryManager.subscribeOnBook(readers[0], books[0]);
            libraryManager.subscribeOnBook(readers[0], books[1]);

            libraryManager.subscribeOnBook(readers[1], books[4]);

            libraryManager.subscribeOnBook(readers[2], books[2]);
            libraryManager.unsubscribeOnBook(readers[2], books[3]);

        }catch (BookException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(libraryManager.whereIsBook(books[4]));
    }
}
