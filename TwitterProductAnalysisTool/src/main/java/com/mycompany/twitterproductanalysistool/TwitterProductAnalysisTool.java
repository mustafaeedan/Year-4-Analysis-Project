/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool;

import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */
public class TwitterProductAnalysisTool {
    
        public static void main(String[] args) {
        TwitterAPI twit = new TwitterAPI();
        ArrayList<String> qr = twit.getQuery("#iphone7 headphone");
        for(String s : qr) {
            System.out.println(s);
        }
        
    }
    
}
