package com.ism.views;

import com.ism.entities.Commande;
import com.ism.services.CommandeServiceI;
import com.ism.services.impl.CommandeServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class CommandeController {

    private final CommandeServiceI commandeService = new CommandeServiceImpl();

    @FXML
    private TextField clientNameField, phoneField, addressField;

    @FXML
    private ComboBox<String> articleComboBox;

    @FXML
    private TextField quantityField, priceField;

    @FXML
    private TableView<?> articleTableView; 

    @FXML
    private Text totalText;

    @FXML
    private Button validateButton, addButton;

    private Commande currentCommande = new Commande();

    @FXML
    private void initialize() {
        articleComboBox.getItems().addAll("Article 1", "Article 2", "Article 3");
    }

    @FXML
    private void handleAddArticle() {
        String article = articleComboBox.getValue();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        double total = commandeService.calculerTotalCommande(currentCommande);
        totalText.setText("Total: " + total);
    }

    @FXML
    private void handleValidateCommande() {
        Commande savedCommande = commandeService.creerCommande(currentCommande);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "commmande fait");
        alert.show();
    }
}
