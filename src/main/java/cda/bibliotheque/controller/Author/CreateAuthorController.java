package cda.bibliotheque.controller.Author;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.AuthorDAO;
import cda.bibliotheque.model.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateAuthorController {

    @FXML
    private DatePicker inputBornDate;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    private final AuthorDAO authorDAO = new AuthorDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Author author = new Author();
        author.setBorn_at(inputBornDate.getValue());
        author.setFirstname(inputFirstName.getText());
        author.setLastname(inputLastName.getText());
        authorDAO.addAuthor(author);
        App.setRoot("authors/authors");
    }

}
