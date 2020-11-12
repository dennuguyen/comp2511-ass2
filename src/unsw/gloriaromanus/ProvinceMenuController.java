package unsw.gloriaromanus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.gloriaromanus.component.HighTax;
import unsw.gloriaromanus.component.LowTax;
import unsw.gloriaromanus.component.NormalTax;
import unsw.gloriaromanus.component.TaxLevel;
import unsw.gloriaromanus.component.VeryHighTax;

public class ProvinceMenuController extends MenuController {

    // https://stackoverflow.com/a/30171444
    @FXML
    private URL location; // has to be called location

    @FXML
    private TextField factionField;

    @FXML
    private TextField wealthField;

    @FXML
    private TextField taxField;

    @FXML
    private TextField soldierField;

    @FXML
    private Button changeTaxButton;

    @FXML
    public void setProvince(String faction, Province province, Boolean isEdit) throws IOException {
        factionField.setText(faction);
        wealthField.setText(Integer.toString(province.getWealth()));
        taxField.setText(province.getTaxLevel().getName());
        soldierField.setText("0");
        if (isEdit) {
            changeTaxButton.setDisable(false);
        }
        else {
            changeTaxButton.setDisable(true);
        }
    }

    @FXML
    public void clickedChangeTax(ActionEvent event) throws IOException {
        switch (taxField.getText().toString()) {
            case "Low":
                taxField.setText("Normal");
                break;
            case "Normal":
                taxField.setText("High");
                break;
            case "High":
                taxField.setText("Very High");
                break;
            case "Very High":
                taxField.setText("Low");
                break;
            default:
                System.out.println("Invalid tax input");
        }
    }

    public TaxLevel getTaxSetting() {
        switch (taxField.getText().toString()) {
            case "Low":
                return new LowTax();
            case "Normal":
                return new NormalTax();
            case "High":
                return new HighTax();
            case "Very High":
                return new VeryHighTax();
            default:
                System.out.println("Invalid tax input");
                return null;
        }
    }
}
