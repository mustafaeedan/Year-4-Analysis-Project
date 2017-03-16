/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustafa
 */
public class FXMLController implements Initializable {

     @FXML //  fx:id="myButton"
     private Button myButton; // Value injected by FXMLLoader

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            myButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AnchorPane page = (AnchorPane) FXMLLoader.load(TestFrame.class.getResource("/fxml/FXML1.fxml"));
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) myButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
    }    
    
}
