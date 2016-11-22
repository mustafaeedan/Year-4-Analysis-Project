/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool;

/**
 *
 * @author Mustafa
 */
public class TwitterProductAnalysisTool {
    
        public static void main(String[] args) {
        /*TwitterAPI twit = new TwitterAPI();
        QueryResult qr = twit.getQuery("#trump");
        if (qr == null) {
            System.out.println("BAD");
        }
        for (Status status : qr.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        } */
        
        SentimentAnalysis st = new SentimentAnalysis();
        System.out.println("HERE" + st.getSentiment("This film was so good"));
    }
    
}
