package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.json.JSONObject;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.util.Duration;
import unsw.gloriaromanus.util.Util;

public class MainMenuController implements Initializable{

    private GloriaRomanusScreen gloriaRomanusScreen;
    private String initMode;
    private World world;
    private List<Faction> players;
    private Victory victory;
    private boolean isEnded;
    private int currentPlayer;

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
        factions.setAll("None", "Rome", "Gaul", "Spain", "Egypt");
        player1ChoiceBox.setItems(factions);
        player2ChoiceBox.setItems(factions);
        player3ChoiceBox.setItems(factions);
        player4ChoiceBox.setItems(factions);
        players = new ArrayList<Faction>();
        World.init("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json");
        world = World.getInstance();
        isEnded = false;
    }

    @FXML
    public void handleNewGame(ActionEvent event) throws IOException, InterruptedException {
        if  (isEmpty() || isDuplicates() || isNone()) {
            if (isEmpty()){
                newGameButton.setText("All fields must be entered");
            }
            else if (isNone()){
                newGameButton.setText("Must have at least 2 players");
            }
            else if (isDuplicates()) {
                newGameButton.setText("Players must be different factions");
            } 
            newGameButton.setStyle("-fx-base: red");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                newGameButton.setStyle(null);
                newGameButton.setText("Start a new game");});
            pause.play(); 
        }
        else {
            generateFaction(player1ChoiceBox.getValue().toString());
            generateFaction(player2ChoiceBox.getValue().toString());
            generateFaction(player3ChoiceBox.getValue().toString());
            generateFaction(player4ChoiceBox.getValue().toString());
            victory = new Victory();
            initMode = "new";
            currentPlayer = 0;
            changeToGameScreen(event);
        }
    }

    private boolean isDuplicates() {
        List<String> list = new ArrayList<String>();
        list.add(player1ChoiceBox.getValue().toString());
        list.add(player2ChoiceBox.getValue().toString());
        list.add(player3ChoiceBox.getValue().toString());
        list.add(player4ChoiceBox.getValue().toString());
        Set<String> set1 = new HashSet<>();
        for(String str : list) {
            if (!str.equals("None") && !set1.add(str)) return true;
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

    private boolean isNone() {
        int count = 0;
        if (!player1ChoiceBox.getValue().toString().equals("None")) count++;
        if (!player2ChoiceBox.getValue().toString().equals("None")) count++;
        if (!player3ChoiceBox.getValue().toString().equals("None")) count++;
        if (!player4ChoiceBox.getValue().toString().equals("None")) count++;
        return count < 2;
    }

    private void generateFaction(String value) {
        switch (value) {
            case "Rome":
                players.add(new Faction("Rome")); break;
            case "Gaul":
                players.add(new Faction("Gaul")); break;
            case "Spain":
                players.add(new Faction("Spain")); break;
            case "Egypt":
                players.add(new Faction("Egypt")); break;
            default:
                return;
        }
    }

    @FXML
    public void handleLoadGame(ActionEvent event) throws IOException, InterruptedException {
        PersistanceFactory pf = new PersistanceFactory();
        JSONObject save = Util.parseJsonFile("save.json");
        if (save == null) return;
        victory = pf.deserializeVictory(save);
        players = pf.deserializePlayers(world, save);
        isEnded = pf.deserializeWinner(save);
        currentPlayer = pf.deserializeCurrentPlayer(save);
        initMode = "load";
        changeToGameScreen(event);
    }

    public void setGloriaRomanusScreen(GloriaRomanusScreen gloriaRomanusScreen) {
        this.gloriaRomanusScreen = gloriaRomanusScreen;
    }

    public void changeToGameScreen(ActionEvent event) throws IOException, InterruptedException {
        GloriaRomanusController controller = gloriaRomanusScreen.getController();
        controller.initData(initMode, currentPlayer, isEnded, world, victory, players);
        gloriaRomanusScreen.start();
    }
}
