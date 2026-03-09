package com.example.map;

import java.util.*;

public class MarkerStyleFactory {
    // I am using a HashMap to store existing styles. 
    // The 'key' acts as a unique signature for a specific style combination.
    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        // I generate a unique string key for the requested style.
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        
        // I check if we've already created this exact style. 
        // If not, I create it, cache it, and then return it. 
        // This ensures only one instance exists for any unique combination.
        if (!cache.containsKey(key)) {
            cache.put(key, new MarkerStyle(shape, color, size, filled));
        }
        return cache.get(key);
    }

    public int cacheSize() {
        return cache.size();
    }
}

/* Initial code
public class MarkerStyleFactory {
    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        return new MarkerStyle(shape, color, size, filled);
    }

    public int cacheSize() {
        return cache.size();
    }
}
*/