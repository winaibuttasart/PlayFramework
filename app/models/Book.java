package models;

import com.sun.org.apache.xpath.internal.operations.Mod;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

//@Entity
public class Book extends BaseModel {

    private static final long serialVersionUID = 1L;

//    @Id
    public Integer id;
    public String title;
    public Double price;
    public String author;



    public static Finder<Long,Book> find = new Finder<>(Book.class);









    public Book(){}

    public Book(Integer id, String title, Double price, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    private static Set<Book> books;

    static {
        books = new HashSet<>();
        books.add(new Book(1, "C++", 150.00, "ABC author"));
        books.add(new Book(2, "JAVA", 270.00, "EFG author"));
    }

    public static Set<Book> allBooks() {
        return books;
    }

    public static Book findById(Integer id) {
        for (Book book : books) {
            if (id.equals(book.id)) {
                return book;
            }
        }
        return null;
    }

    public static void add(Book book) {
        books.add(book);
    }

    public static boolean remove(Book book) {
        return books.remove(book);
    }

}
