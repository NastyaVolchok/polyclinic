package config;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class FormValidator {
    public void disabledComponent(Node node){
        node.setDisable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    node.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void validationInput(TextInputControl input, String message){
        String inputTex=input.getText();
        input.setStyle("-fx-background-color: #00005e; -fx-text-fill: #ff0000;");
        input.setText(message);
        input.setEditable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    input.setStyle("");
                    input.setText(inputTex);
                    input.setEditable(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void validatorComboBox(ComboBox<String>comboBox){
        comboBox.setStyle("-fx-background-color: #00005e; -fx-text-fill: #ff0000;");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    comboBox.setStyle("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void viewArlet(Label node, String message){
        node.setText(message);
        node.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    node.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public void statusSec(TextInputControl input){
        input.setStyle("-fx-background-color: lime");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    input.setStyle("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void statusDange(TextInputControl input){
        input.setStyle("-fx-background-color: red");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    input.setStyle("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void visableArlet(Label label){
        label.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    label.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
