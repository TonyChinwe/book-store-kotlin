package com.phisoft.bookstorekotlin.managements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * List of exposed Api layer
 */
@Component
@Endpoint(id="books")
public class FeatureEndPoint {
    private final Map<String,Feature> featureMap=new ConcurrentHashMap<>();

    public FeatureEndPoint(){
        featureMap.put("books",new Feature(true));
        featureMap.put("book's attribute",new Feature(true));
        featureMap.put("account",new Feature(true));
    }

    @ReadOperation
    public Map<String,Feature> features(){
        return featureMap;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName){
     return featureMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature{
        private boolean isEnabled;

    }
}
