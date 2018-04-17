package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.books.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class BookController extends Controller {


    @Inject
    FormFactory formFactory;

    private Result BOOK_GO_HOME = Results.redirect(
            routes.BookController.bookindex()
    );

    public Result bookindex() {
        Set<Book> books = Book.allBooks();
//        List<Book> books = Book.find.all();
        return ok(bookindex.render(books));
    }

    public Result bookcreate() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(bookcreate.render(bookForm));
    }

    public Result booksave() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        Book.add(book);
//        book.save();

//        return redirect(routes.BookController.bookindex());
        return BOOK_GO_HOME;
    }

    public Result bookedit(Integer id) {
        Book book = Book.findById(id);
//        Book book = Book.find.byId(Long.valueOf(id));

        if (book == null) {
            return notFound("Book Not Found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(bookedit.render(bookForm));
    }

    public Result bookupdate() {
//        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
//        Book book = bookForm.get();

        Book book = formFactory.form(Book.class).bindFromRequest().get();
        Book oldBook = Book.findById(book.id);
//        Book oldBook = Book.find.byId(Long.valueOf(book.id));

        if (oldBook == null) {
            return notFound("Book Not Found");
        }
        oldBook.title = book.title;
        oldBook.author = book.author;
        oldBook.price = book.price;
        //add new
//        oldBook.update();


        return BOOK_GO_HOME;
    }

    public Result bookdestroy(Integer id) {
        Book book = Book.findById(id);
//        Book book = Book.find.byId(Long.valueOf(id));

        if (book == null) {
            return notFound("Book Not Found");
        }
//        Book.remove(book);
//        Book.delete();
        Book.find.deleteById(Long.valueOf(id));

        return BOOK_GO_HOME;
    }

    public Result bookshow(Integer id) {
        Book book = Book.findById(id);
//        Book book = Book.find.byId(Long.valueOf(id));

        if (book == null) {
            return notFound("Book Not Found");
        }
        return ok(bookshow.render(book));
    }

}
