package task4.dao;

import task4.classes.Library;
import task4.classes.MemoryManager;
import task4.enums.BookStatus;
import task4.exceptions.BookException;
import task4.interfaces.Book;
import task4.interfaces.Manager;
import task4.interfaces.Reader;

/**
 * Created by prokop on 9.10.16.
 */
public class LibraryManager implements Manager{

    private MemoryManager<Book> bookMemoryManager;
    private MemoryManager<Reader> readerMemoryManager;

    private Library library;

    public LibraryManager() {
        this.bookMemoryManager = new MemoryManager<>();
        this.readerMemoryManager = new MemoryManager<>();
    }

    public LibraryManager(Library library) {
        this.library = library;
        this.bookMemoryManager = new MemoryManager<>();
        this.readerMemoryManager = new MemoryManager<>();
    }

    public void addBook(Book book){
        for (int i = 0; i < library.getBooks().length; i++) {

            if(bookMemoryManager.isFull(library.getBooks())){
                this.library.setBooks(bookMemoryManager.extendArray(library.getBooks()));
            }

            if(library.getBooks()[i] == null) {
                library.getBooks()[i] = book;
                return;
            }
        }
    }

    public void addReader(Reader reader){
        for (int i = 0; i < library.getReaders().length; i++) {

            if(readerMemoryManager.isFull(library.getReaders())){
                this.library.setReaders(readerMemoryManager.extendArray(library.getReaders()));
            }

            if(library.getReaders()[i] == null) {
                library.getReaders()[i] = reader;
                return;
            }
        }
    }

    public void subscribeOnBook(Reader reader, Book book) throws BookException {
        if(book.getStatus() == BookStatus.BUSY) {
            throw new BookException(BookStatus.BUSY.getMessage());
        }
        reader.addBook(book);
        book.setReader(reader);
        book.setStatus(BookStatus.BUSY);
    }

    public void unsubscribeOnBook(Reader reader, Book book) throws BookException {
        if(book.getStatus() == BookStatus.FREE) {
            throw new BookException(BookStatus.FREE.getMessage());
        }
        reader.deleteBook(book);
        book.setReader(null);
        book.setStatus(BookStatus.FREE);

    }

    public Reader whereIsBook(Book book) {
        for (Reader reader: this.library.getReaders()) {
            for(Book b: reader.getBooks()) {
                if(book.equals(b)) {
                    return reader;
                }
            }
        }
        return null;
    }
}
