package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SideMenuController extends MenuController{

    // https://stackoverflow.com/a/30171444
    @FXML
    private URL location; // has to be called location

    @FXML
    private Label currentYear;

    @FXML
    private Label currentFaction;

    @FXML
    public void clickedEndTurn(ActionEvent e) throws IOException {
        getParent().clickedEndTurn(e);
    }

    @FXML
    public void clickedSaveGame(ActionEvent e) throws IOException {
        getParent().clickedSaveGame(e);
    }
}
