package com.mycompany.twitterproductanalysistool.GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mustafa
 */
public class FXML2Controller implements Initializable {
     @FXML
     private ListView butList; // Value injected by FXMLLoader
     @FXML
     private AnchorPane featurePage;
     
     private ObservableList<String> data = FXCollections.observableArrayList();
     private ArrayList<ArrayList<String>> tempConts = new ArrayList<>();
     private ArrayList<Map<String, String>> tempDates = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        butList.setCellFactory(new Callback<ListView<String>, ListCell>() {
            @Override
            public ListCell call(ListView<String> param) {
                return new ButtonCell(tempConts, tempDates);
            }
        });  
        butList.setItems(data);
    }   
    
    public void addItem(String item) {
        data.add(item);
    }
   
    public void addTempCountries(ArrayList<String> conts) {
        tempConts.add((ArrayList<String>)conts.clone());
    } 
    
    public void addTempDates(Map<String, String> dts) {
        tempDates.add(dts);
    }
}
