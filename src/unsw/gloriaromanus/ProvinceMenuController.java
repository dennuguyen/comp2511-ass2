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
    private TextField wealthGrowthField;

    @FXML
    private TextField wealthField;

    @FXML
    private TextField taxField;

    @FXML
    private TextField soldierField;

    @FXML
    private Button changeTaxButton;

    private Province currentProvince;

    private TaxLevel low = new LowTax();
    private TaxLevel normal = new NormalTax();
    private TaxLevel high = new HighTax();
    private TaxLevel veryHigh = new VeryHighTax();

    @FXML
    public void setProvince(String faction, Province province, Boolean isEdit) throws IOException {
        currentProvince = province;
        factionField.setText(faction);
        wealthField.setText(Integer.toString(province.getWealth()));
        wealthGrowthField.setText(Integer.toString(province.getWealthGrowth()));
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
                getParent().setTaxLevel(currentProvince, normal);
                //taxField.setText("Normal");
                break;
            case "Normal":
                getParent().setTaxLevel(currentProvince, high);
                //taxField.setText("High");
                break;
            case "High":
                getParent().setTaxLevel(currentProvince, veryHigh);
                //taxField.setText("Very High");
                break;
            case "Very High":
                getParent().setTaxLevel(currentProvince, low);
                //taxField.setText("Low");
                break;
            default:
                System.out.println("No province selected. Do nothing.");
        }
    }
}
