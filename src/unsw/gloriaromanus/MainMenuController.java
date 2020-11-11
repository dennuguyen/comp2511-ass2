package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.util.Duration;

public class MainMenuController implements Initializable{

    GloriaRomanusScreen gloriaRomanusScreen;

    @FXML
    private Button newGameButton;

    @FXML
    private ChoiceBox<String> battleResolverChoiceBox;
    
    @FXML
    private ChoiceBox<String> player1ChoiceBox;
    
    @FXML
    private ChoiceBox<String> player2ChoiceBox;

    @FXML
    private ChoiceBox<String> player3ChoiceBox;

    @FXML
    private ChoiceBox<String> player4ChoiceBox;

    @FXML
    private Button loadGameButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> battle = FXCollections.observableArrayList();
        ObservableList<String> factions = FXCollections.observableArrayList();
        battle.addAll("Basic Battle Resovler");
        battleResolverChoiceBox .setItems(battle);
        factions.setAll("None", "Romans", "Gauls", "Spanish", "Egyptians");
        player1ChoiceBox.setItems(factions);
        player2ChoiceBox.setItems(factions);
        player3ChoiceBox.setItems(factions);
        player4ChoiceBox.setItems(factions);
    }

    @FXML
    public void handleNewGame(ActionEvent event) {
        if (isEmpty()){
            newGameButton.setText("All fields must be entered");
            newGameButton.setStyle("-fx-base: red");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                newGameButton.setStyle(null);
                newGameButton.setText("Start a new game");});
            pause.play(); 
        }
        else if (isDuplicates()) {
            newGameButton.setText("Players must be different factions");
            newGameButton.setStyle("-fx-base: red");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                newGameButton.setStyle(null);
                newGameButton.setText("Start a new game");});
            pause.play();  
        }
        else {
            generateFaction(player1ChoiceBox.getValue());
            generateFaction(player2ChoiceBox.getValue());
            generateFaction(player3ChoiceBox.getValue());
            generateFaction(player4ChoiceBox.getValue());
            gloriaRomanusScreen.start();
        }
    }

    private boolean isDuplicates() {
        List<String> list = new ArrayList<String>();
        list.add(player1ChoiceBox.getValue());
        list.add(player2ChoiceBox.getValue());
        list.add(player3ChoiceBox.getValue());
        list.add(player4ChoiceBox.getValue());
        Set<String> set1 = new HashSet<>();
        for(String str : list) {
            if (str != "None" && !set1.add(str)) return true;
        }
        return false;        
    }

    private boolean isEmpty() {
        return battleResolverChoiceBox.getValue() == null || 
                player1ChoiceBox.getValue() == null ||
                player2ChoiceBox.getValue() == null ||
                player3ChoiceBox.getValue() == null ||
                player4ChoiceBox.getValue() == null;
    }

    private Faction generateFaction(String value) {
        switch (value) {
            case "Romans":
                return new Faction("Romans");
            case "Gauls":
                return new Faction("Gauls");
            case "Spanish":
                return new Faction("Spanish");
            case "Egyptians":
                return new Faction("Egyptians");
            default:
                return null;
        }
    }

    @FXML
    public void handleLoadGame(ActionEvent event) {
        gloriaRomanusScreen.start();
    }

    public void setGloriaRomanusScreen(GloriaRomanusScreen gloriaRomanusScreen) {
        this.gloriaRomanusScreen = gloriaRomanusScreen;
    }

    
}
