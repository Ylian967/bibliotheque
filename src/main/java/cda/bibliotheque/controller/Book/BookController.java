package cda.bibliotheque.controller.Book;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class BookController {

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, LocalDate> colReleaseDate;

    @FXML
    private TableColumn<Book, Boolean> colIsAvailable;

    @FXML
    private TableColumn<Book, Void> colActions;

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colReleaseDate.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getReleaseDate()));
        colIsAvailable.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isAvailable()).asObject());

        colActions.setCellFactory(cell -> new TableCell<>() {
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);

            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToEdit = booksTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("books/edit-book.fxml"));
                        Parent parent = loader.load();

                        EditBookController editBookController = loader.getController();
                        editBookController.setBook(bookToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit -> " + e);
                    }
                });

                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToDelete = booksTable.getItems().get(index);
                    bookDAO.deleteBook(bookToDelete.getId());
                    loadBooks();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });

        loadBooks();
    }

    private void loadBooks() {
        bookList.setAll(bookDAO.getAllBooks());
        booksTable.setItems(bookList);
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("books/create-book");
    }
}