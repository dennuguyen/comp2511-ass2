package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SideMenuController extends MenuController{

    private int startYear;

    // https://stackoverflow.com/a/30171444
    @FXML
    private URL location; // has to be called location

    @FXML
    private TextArea victoryConditions;
    
    @FXML
    private TextField currentYear;
    
    @FXML
    private TextField currentFaction;

    @FXML
    public void initialize() {
        startYear = 700;
    }

    @FXML
    public void initData() {
        GloriaRomanusController controller = getParent();
        String vc = controller.getVictoryCondition().nameString();
        System.out.println(vc);
        victoryConditions.setText(vc);
        currentYear.setText(Integer.toString(startYear) + " BC");
        currentFaction.setText(controller.getCurrentFaction());
    }

    @FXML
    public void clickedEndTurn(ActionEvent e) throws IOException {
        getParent().clickedEndTurn(e);
    }

    @FXML
    public void setVictoryMessage(String player) throws IOException {
        victoryConditions.setStyle("-fx-control-inner-background:#c00000;-fx-text-fill: #ffffff; ");
        victoryConditions.setText(player.toUpperCase() + " WON!");
    }

    @FXML
    public void setCurrentYear(int turn) throws IOException {
        currentYear.setText(Integer.toString(startYear - turn));
    }

    @FXML
    public void setCurrentFaction(String faction) throws IOException {
        currentFaction.setText(faction);
    }

    @FXML
    public void clickedSaveGame(ActionEvent e) throws IOException {
        getParent().clickedSaveGame(e);
    }
}
