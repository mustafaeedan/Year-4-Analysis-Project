package com.mycompany.twitterproductanalysistool;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import java.util.HashMap;
import java.util.Map;
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
        public static void main(String[] args) {
        AlchemyLanguage service = new AlchemyLanguage();
        service.setApiKey("75b2e7961fd859b16c17c1a212794f27d60d1828");

        Map<String,Object> params = new HashMap<String, Object>();
        params.put(AlchemyLanguage.TEXT, "IBM Watson won the Jeopardy television show hosted by Alex Trebek");
        Keywords sentiment = service.getKeywords(params).execute();

System.out.println(sentiment);
    }
    
}
