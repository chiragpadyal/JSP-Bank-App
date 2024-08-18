package com.swabhav;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger instance;
    
    private Logger() {
        // Private constructor to prevent instantiation
    }
    
    public static Logger getInstance() {
        if (instance == null) {
        	instance = new Logger();
        	return instance;
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println(formatMessage("INFO", message));
    }
    
    public void error(String message) {
        System.err.println(formatMessage("ERROR", message));
    }
    
    private String formatMessage(String level, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());
        return String.format("%s [%s] %s", timestamp, level, message);
    }
}

