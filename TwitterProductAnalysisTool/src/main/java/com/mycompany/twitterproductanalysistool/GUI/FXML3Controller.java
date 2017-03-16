package com.mycompany.twitterproductanalysistool.GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mustafa
 */
public class FXML3Controller implements Initializable {
    
    @FXML
    private TextArea countries;
    @FXML
    private AnchorPane graphPane;
    
    private ObservableList<String> cList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void addCountry(String s) {
        String temp = countries.getText();
        if(temp.isEmpty()) {
            countries.setText(s);
        }
        else {
            countries.setText(temp + "\n" + s);
        }
    }
    
    public void createGraph(Map<String,String> dates) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        
        lineChart.setTitle("Sentiment of Feature");
        
        XYChart.Series negSeries = new XYChart.Series();
        negSeries.setName("Negative Tweets");
        XYChart.Series neuSeries = new XYChart.Series();
        neuSeries.setName("Neutral Tweets");
        XYChart.Series posSeries = new XYChart.Series();
        posSeries.setName("Positive Tweets");
        
        for (Map.Entry<String,String> entry : dates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            String[] arrVal = value.split(":");
            posSeries.getData().add(new XYChart.Data(key, Integer.parseInt(arrVal[0])));
            neuSeries.getData().add(new XYChart.Data(key, Integer.parseInt(arrVal[1])));
            negSeries.getData().add(new XYChart.Data(key, Integer.parseInt(arrVal[2])));
        }
        
        lineChart.getData().addAll(posSeries, neuSeries, negSeries);
        graphPane.getChildren().add(lineChart);
        
        

        for (Map.Entry<String,String> entry : dates.entrySet()) {
          String key = entry.getKey();
          String value = entry.getValue();
          System.out.println(key + " : " + value);
          // do stuff
        }   
    }
    
}
