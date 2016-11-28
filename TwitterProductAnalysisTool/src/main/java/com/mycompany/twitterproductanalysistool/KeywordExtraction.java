package com.mycompany.twitterproductanalysistool;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keyword;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mustafa
 */
public class KeywordExtraction {
    private AlchemyLanguage service;
    
    public KeywordExtraction () {
       service = new AlchemyLanguage();
       service.setApiKey("75b2e7961fd859b16c17c1a212794f27d60d1828");
    }
    
    public ArrayList<String> getKeywords (String s) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(AlchemyLanguage.TEXT, s);
        Keywords sentiment = service.getKeywords(params).execute();
        List<Keyword> kWords = sentiment.getKeywords();
        ArrayList<String> strWords = new ArrayList<>();
        for(Keyword k : kWords) {
            strWords.add(k.getText());
        }
        return strWords;
    }
    
}
