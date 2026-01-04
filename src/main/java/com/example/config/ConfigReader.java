package com.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try{
            FileInputStream file = new FileInputStream("config.properties");
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Configuration file not foud: " + e.getMessage());
        } 
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}
