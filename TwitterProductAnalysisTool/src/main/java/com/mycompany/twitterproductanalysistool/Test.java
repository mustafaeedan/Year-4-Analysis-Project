/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mustafa
 */
public class Test {
    public static void main(String[] args) throws IOException{
        SentimentAnalysis sa = new SentimentAnalysis();
        System.out.println(sa.getSentiment("Q: What is the most significant change you've seen in the world in the last 10 years? A: iphone7 headphone jack po."));
    }
}
// the regex is <h([0-9]+).*?>.*<\/h([0-9]+)>.*?<\/p>