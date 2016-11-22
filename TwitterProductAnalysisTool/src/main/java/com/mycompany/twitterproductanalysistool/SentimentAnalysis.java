/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterproductanalysistool;
import java.util.Properties;
import java.util.List;
import java.io.IOException;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentUtils;
import edu.stanford.nlp.util.CoreMap;

/**
 *
 * @author Mustafa
 */
public class SentimentAnalysis {
    
    private StanfordCoreNLP pipeline;
    
    public SentimentAnalysis() {
        Properties p = new Properties();
        p.setProperty("annotators",
            "tokenize, ssplit, pos, lemma, parse, sentiment");
        pipeline = new StanfordCoreNLP(p);
    }
    
    public String getSentiment(String text) {
        Annotation annotation = pipeline.process(text);
        List<CoreMap> sentences = annotation
                .get(CoreAnnotations.SentencesAnnotation.class);
        String fSentiment = "";
        for(CoreMap sentence : sentences) {
            String sentiment = sentence
                    .get(SentimentCoreAnnotations.SentimentClass.class);
            //System.out.println(sentiment + "\t" + sentence);
            fSentiment = fSentiment + sentiment;
            
        }
        return fSentiment;
    }
    
}
