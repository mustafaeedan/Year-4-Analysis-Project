/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.List;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Mustafa
 */
public class TwitterAPI {
    private Twitter twitter;
    private QueryResult result;
    private ArrayList<String> tweets;
    private ArrayList<String> tweetCountries;
    private ArrayList<Date> tweetDates;
    
    public TwitterAPI() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("ogluOfaFe00xjVQ5WfynhkWQY")
            .setOAuthConsumerSecret("l4yL7iz6J1zEBaY4ownHu9VnIu2zSLuELVyzxykgoSEF3vzSup")
            .setOAuthAccessToken("1101064884-uMgjzYHHo82fRZdwWbvjNWs8ZKqoZj0xsKB4jnO")
            .setOAuthAccessTokenSecret("WyCAT00L4FK0lOQiXhshNXpS5bHVFoVsaoYGlJYVylWYm");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }
    
    public ArrayList<String> getQuery(String qry) {
        tweets = new ArrayList<>();
        tweetCountries = new ArrayList<>();
        tweetDates = new ArrayList<>();
        try {
            Query query = new Query(qry);
            query.setCount(99);
            result = twitter.search(query);
            for (Status status : result.getTweets()) {
                tweets.add(status.getText());
                tweetDates.add(status.getCreatedAt());
                if(status.getUser() == null) {
                    tweetCountries.add("NO COUNTRY");
                }
                else {
                    tweetCountries.add(status.getUser().getLocation());
                }
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        for(int i = 0; i < tweets.size(); i++) {
            String s = tweets.get(i);
            tweets.set(i, s.replace("\n", ""));
        }
        return tweets;
    }
    
    public ArrayList<String> getCountries() {
        if (tweetCountries.size() == tweets.size()) {
            return tweetCountries;
        }
        else {
            System.out.println("SIZE IS DIFFERENT");
        }
        return null;
    }
    
    public ArrayList<Date> getDates() {
        if (tweetDates.size() == tweets.size()) {
            return tweetDates;
        }
        else {
            System.out.println("SIZE IS DIFFERENT DATES");
        }
        return null;
    }
    
}
