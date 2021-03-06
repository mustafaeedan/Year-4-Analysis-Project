package com.mycompany.twitterproductanalysistool;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keyword;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.service.exception.BadRequestException;
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
       service.setApiKey("f95e606f55199691a84a2808104210565e32663b");
    }
    
    public ArrayList<String> getKeywords (String s) {
        Map<String,Object> params = new HashMap<String, Object>();
        ArrayList<String> strWords = new ArrayList<>();
        params.put(AlchemyLanguage.TEXT, s);
        try {
            Keywords sentiment = service.getKeywords(params).execute();
            List<Keyword> kWords = sentiment.getKeywords();
            for(Keyword k : kWords) {
                strWords.add(k.getText());
            }
        } catch (BadRequestException e) {
            //do nothing
        }
        return strWords;
    }
    
}
