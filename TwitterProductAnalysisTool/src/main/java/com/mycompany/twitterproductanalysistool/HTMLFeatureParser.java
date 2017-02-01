/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Mustafa
 */
public class HTMLFeatureParser {
    private String html;
    private String url;
    
    public HTMLFeatureParser(String u) throws IOException {
        html = "";
        url = u;
        setHTML();
    }
    
    public void setHTML() throws IOException {
        URL myURL = new URL(url);
        URLConnection conn = myURL.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader (
                conn.getInputStream(), "UTF-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null)
            sb.append(line);
        br.close();
        
        html = sb.toString();
    }
    
    public String getHTML() {
        return html;
    }
    
}
