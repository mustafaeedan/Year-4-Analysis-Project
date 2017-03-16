/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mustafa
 */
public class ButtonCell extends ListCell<String> {
    private HBox hbox = new HBox();
    Label lab = new Label();
    Label sLab = new Label();
    Button but = new Button("View");
    Pane pane = new Pane();
    String last;
    
    public ButtonCell(final ArrayList<ArrayList<String>> conts, final ArrayList<Map<String,String>> dats) {
        super();
        hbox.setSpacing(15);
        hbox.getChildren().addAll(lab, pane, sLab, but);
        HBox.setHgrow(pane, Priority.ALWAYS);
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event) {
                 try {
                    
                    String featString = lab.getText();
                    String[] arrFtStr = featString.split(" ");
                    String strNum = arrFtStr[1].substring(0,arrFtStr[1].length()-1);
                    int index = Integer.parseInt(strNum) - 1;
                     
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXML3.fxml"));
                    TabPane page = loader.load();
                    FXML3Controller controller = loader.getController();
                    
                    ArrayList<String> featureCounts = conts.get(index);
                    
                    for(String country : featureCounts) {
                        controller.addCountry(country);
                    }
                    controller.createGraph(dats.get(index));
                    //add implementation for Dates
                    Stage primaryStage = (Stage) but.getScene().getWindow();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(primaryStage);
                    Scene scene = new Scene(page);
                    stage.setScene(scene);
                    stage.show();
                 } catch (IOException e) {
                     System.out.println(e);
                 }
             }
        });
    }
    
    @Override
    public void updateItem(String s, boolean empty) {
        super.updateItem(s, empty);
        if(empty) {
            last = null;
            setGraphic(null);
        }
        else {
            last = s;
            String[] info = s.split("\\|");
            if (info.length == 2) {
                String feat = info[0];
                String sent = info[1];
                lab.setText(feat);
                sLab.setText(sent);
            }
            setGraphic(hbox);
            
        }
    }
    
}
