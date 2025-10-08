package cda.bibliotheque.controller.Book;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditBookController {

    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    private TextField inputTitle;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private CheckBox inputIsAvailable;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book updatedBook = book.get();
        updatedBook.setTitle(inputTitle.getText());
        updatedBook.setReleaseDate(inputReleaseDate.getValue());
        updatedBook.setAvailable(inputIsAvailable.isSelected());
        bookDAO.updateBook(updatedBook);
        App.setRoot("books/books");
    }

    @FXML
    public void initialize() {
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                inputTitle.setText(newBook.getTitle());
                inputReleaseDate.setValue(newBook.getReleaseDate());
                inputIsAvailable.setSelected(newBook.isAvailable());
            }
        });
    }

    public void setBook(Book book) {
        this.book.set(book);
    }
}