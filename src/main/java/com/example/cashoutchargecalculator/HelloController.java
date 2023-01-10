package com.example.cashoutchargecalculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    public RadioButton bKash;
    public RadioButton nagad;
    public TextField amountField;
    public Label charge;
    public Button calculate;


    @FXML
    public void initialize() {
        calculate.setDisable(true);
        calculate.setOnAction(e -> {
            if (!(bKash.isSelected() || nagad.isSelected())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Please select one");
                alert.showAndWait();
            } else {
                double chargeAmount = calculateAmount();
                if (chargeAmount > 25000) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Amount should be less than 25000");
                    alert.showAndWait();
                    return;
                } else if (bKash.isSelected()) {
                    chargeAmount = (chargeAmount / 100) * 1.85;
                } else if (nagad.isSelected()) {
                    chargeAmount = (chargeAmount / 100) * 1.2;
                }
                charge.setVisible(true);
                charge.setText(String.format("Your cash-out charge is = %.2f", chargeAmount));
            }
        });
    }

    private double calculateAmount() {
        try {
            String amountText = amountField.getText();
            return Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Invalid Amount!");
            alert.showAndWait();
        }
        return 0.0;
    }

    @FXML
    private void handleKeyReleased() {
        String amounts = amountField.getText();
        boolean disableButton = amounts.isEmpty() || amounts.trim().isEmpty();
        calculate.setDisable(disableButton);
    }
}