/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool.GUI;

import com.mycompany.twitterproductanalysistool.HTMLFeatureParser;
import com.mycompany.twitterproductanalysistool.KeywordExtraction;
import com.mycompany.twitterproductanalysistool.SentimentAnalysis;
import com.mycompany.twitterproductanalysistool.TwitterAPI;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mustafa
 */
public class FXML1Controller implements Initializable {

     @FXML //  fx:id="myButton"
     private AnchorPane inputPane; // Value injected by FXMLLoader
     @FXML //  fx:id="myButton"
     private Button enterURL; // Value injected by FXMLLoader
     @FXML
     private Button add; // Value injected by FXMLLoader
     @FXML
     private Button delete; // Value injected by FXMLLoader
     @FXML
     private Button submit; // Value injected by FXMLLoader
     @FXML
     private ListView fList; // Value injected by FXMLLoader
     @FXML
     private TextField hashTag; 
     
     private ObservableList<String> data = FXCollections.observableArrayList();
     private ArrayList<ArrayList<String>> keywords = new ArrayList<>();
     private boolean flag = false;
     private ComboBox<String> choiceList = new ComboBox<String>();
     private Separator sep = new Separator();
     private Button save = new Button("Save");
     private Label selectFeat = new Label("Select the keyword for this feature:");
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            fList.setItems(data);
            fList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            fList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    try {
                        if(click.getClickCount() == 2) {
                            if(flag == false) {
                                add.getScene().getWindow().setWidth(820);
                                fList.getScene().getWindow().centerOnScreen();
                                sep.setOrientation(Orientation.VERTICAL);
                                sep.setPrefSize(5, 436);
                                choiceList.setPrefSize(184, 25);
                            
                                inputPane.getChildren().add(sep);
                                inputPane.getChildren().add(selectFeat);
                                inputPane.getChildren().add(choiceList);
                                inputPane.getChildren().add(save);
                            
                                selectFeat.relocate(604, 176);
                                sep.relocate(590, 22);
                                choiceList.relocate(604, 205);
                                save.relocate(676, 426);
                                flag = true;
                            }
                            
                            if(flag == true) {
                                final int indexList = fList.getSelectionModel().getSelectedIndex();
                                System.out.println("INDEX IS " + indexList);
                                final ArrayList<String> selections = keywords.get(indexList);
                                System.out.println("SELECTIONS IS " + selections);
                                ObservableList<String> obSelect = FXCollections.observableList(selections);
                                choiceList.setItems(obSelect);
                                choiceList.getSelectionModel().select(0);
                                save.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {          
                                      int newKeySearch = choiceList.getSelectionModel().getSelectedIndex();
                                      System.out.println(choiceList.getSelectionModel().getSelectedIndex());
                                      Collections.swap(selections, 0, newKeySearch);
                                      System.out.println("FIRST ELEMENT IS " + selections.get(0));
                                      keywords.set(indexList, selections);
                                      System.out.println(keywords.get(indexList));
                                      clearAddedSideBar();
                                      fList.getScene().getWindow().centerOnScreen();
                                      fList.getSelectionModel().clearSelection();
                                      flag = false;
                                    }
                                });
                                
                            }
                           
                        }
                    } catch (NullPointerException e) {
                    }
                }
            });
            
            final KeywordExtraction key = new KeywordExtraction();
            
            enterURL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter URL");
                dialog.setHeaderText("Enter a URL for the particular product");
                dialog.setContentText("Please enter the URL:");
                
                Optional<String> result = dialog.showAndWait();
                String url = "";

                if (result.isPresent()) {
                    url = result.get();
                    ArrayList<String> a = new ArrayList<>();
                    try {
                        HTMLFeatureParser h = new HTMLFeatureParser(url);
                        String s = h.getHTML();
                        int count = 0;
                        s = s.replace("\t", "");
                        Matcher m = Pattern.compile("<h([0-9]+).*?>.*?</h([0-9]+)>.*?</p>(<p>.*?</p>)?")
                            .matcher(s);
                        while(m.find()) {
                            a.add(m.group().replaceAll("</h([0-9]+)>", "; ").replaceAll("\\<.*?>",""));
                            count++;
                        }
                        Iterator<String> iterator = a.iterator();
                        while (iterator.hasNext()) {
                            String feat = iterator.next();
                            int spaces = feat.length() - feat.replace(" ", "").length();
                            if(feat.length() > 2000 || spaces > 250 || spaces < 20) {
                                iterator.remove(); 
                            }
                        }
                    } catch(IOException e) {
                        System.out.println("URL is invalid");
                    }

                    if(a.size() > 0) {
                        for(String f : a) {
                            data.add(f);
                            keywords.add(key.getKeywords(f));
                        }
                    }
                }
                else {
                    System.out.println("You did not enter anything");
                }
                
            }
            });
           
            add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {          
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New Feature");
                dialog.setHeaderText("Enter a new feature");
                dialog.setContentText("Please enter a feature and\nthe description of the feature:");
                
                Optional<String> result = dialog.showAndWait();
                String feat = "";

                if (result.isPresent()) {
                    if(result.get().equals("")) {
                           System.out.println("You did not enter anything");
                    }
                    else {
                        feat = result.get();
                        data.add(feat);
                        keywords.add(key.getKeywords(feat));
                        
                    }
                }
            }
            });
            
            delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {  
                ObservableList<Integer> sel = fList.getSelectionModel().getSelectedIndices();
                ArrayList<Integer> tempSel = new ArrayList<>();
                for(int i : sel) {
                    System.out.println(i);
                    tempSel.add(i);
                }
                Collections.sort(tempSel, Collections.reverseOrder());
                System.out.println(tempSel);
                if(tempSel.size() == data.size()) {
                    data.clear();
                    keywords.clear();
                }
                else {
                    for(int i : tempSel) {
                        data.remove(i);
                        keywords.remove(i);
                    }
                }
            }        

            });
            
            submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXML2.fxml"));
                    AnchorPane page = loader.load();
                    
                    
                    FXML2Controller controller = loader.getController();
                    SentimentAnalysis sa = new SentimentAnalysis();
                    TwitterAPI twitter = new TwitterAPI();
                    int count = 1;
                    for(ArrayList<String> list : keywords) {
                        Map<String, String> map = new HashMap<String, String>();
                        ArrayList<String> tweets = twitter.getQuery(hashTag.getText() + " " + list.get(0));
                        ArrayList<Date> dates = twitter.getDates();
                        
                        for(Date date : dates) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            
                            String key = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + " " + Integer.toString(cal.get(Calendar.MONTH)) + " " + Integer.toString(cal.get(Calendar.YEAR));
                            if(map.containsKey(key)) {
                                
                            }
                            else {
                                map.put(key, "0:0:0");
                            }
                        }
                        
                        
                        int pos = 0;
                        int neu = 0;
                        int neg = 0;
                        int i = 0;
                        for (String tweet : tweets) {
                            Date tempDate = dates.get(i);
                            Calendar tempCal = Calendar.getInstance();
                            tempCal.setTime(tempDate);
                            String key = Integer.toString(tempCal.get(Calendar.DAY_OF_MONTH)) + " " + Integer.toString(tempCal.get(Calendar.MONTH)) + " " + Integer.toString(tempCal.get(Calendar.YEAR));
                            String value = map.get(key);
                            String[] sent = value.split(":");
                            
                            
                            int selectedSentiment = sa.getSentiment(tweet);
                            if (selectedSentiment > 0) {
                                int p = Integer.parseInt(sent[0]);
                                p++;
                                sent[0] = Integer.toString(p);
                                pos++;
                            }
                            else if (selectedSentiment < 0) {
                                int nn = Integer.parseInt(sent[2]);
                                nn++;
                                sent[2] = Integer.toString(nn);
                                neg++;
                            }
                            else {
                                int n = Integer.parseInt(sent[1]);
                                n++;
                                sent[1] = Integer.toString(n);
                                neu++;
                            }
                            
                            StringBuilder sb = new StringBuilder();
                            for (int l = 0; l < sent.length; l++) {
                                if(l == 0) {
                                    sb.append(sent[l]);
                                }
                                else {
                                    sb.append(":" + sent[l]);
                                }
                            }
                            String result = sb.toString();
                            map.put(key, result);
                            i++;
                        }
                        
                        controller.addItem("Feature " + count + ": " + list.get(0) + "|" + "P: " + pos + " N: " + neu + " N: " + neg);
                        controller.addTempCountries(twitter.getCountries());
                        controller.addTempDates(map);
                        count++;
                    }
                    
                    if(flag) { clearAddedSideBar(); }
                    
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) submit.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
    }
    
    public void clearAddedSideBar() {
        inputPane.getChildren().remove(sep);
        inputPane.getChildren().remove(selectFeat);
        inputPane.getChildren().remove(choiceList);
        inputPane.getChildren().remove(save);
        add.getScene().getWindow().setWidth(615);
    }
                
    
}