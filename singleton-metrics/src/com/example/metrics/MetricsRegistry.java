package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // I added volatile here to ensure that changes made by one thread to this variable are immediately visible to all other threads, 
    // preventing them from seeing a partially initialized instance.
    private static volatile MetricsRegistry INSTANCE; 
    
    private final Map<String, Long> counters = new HashMap<>();

    // I made the constructor private so that external classes cannot use new MetricsRegistry(). 
    // I also added a check to throw an exception if the constructor is called when an instance already exists .
    // This blocks reflection attacks that try to bypass the private modifier.
    private MetricsRegistry() {
        if (INSTANCE != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    // I implemented double-checked locking here. 
    // 1. The first check is for performance (avoiding sync if the instance exists).
    // 2. The synchronised block ensures only one thread can enter the creation phase.
    // 3. The second check ensures that if another thread created the instance while the current thread was waiting for the lock we dont create it again.
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (MetricsRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    // I added this method to preserve the singleton during deserialisation.
    // Without this java would create a new object when reading the registry from a stream. 
    // This tells Java to return our existing instance instead.
    protected Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

}

/* Initial code
 public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static MetricsRegistry INSTANCE; // BROKEN: not volatile, not thread-safe
    private final Map<String, Long> counters = new HashMap<>();

    // BROKEN: should be private and should prevent second construction
    public MetricsRegistry() {
        // intentionally empty
    }

    // BROKEN: racy lazy init; two threads can create two instances
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MetricsRegistry();
        }
        return INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // TODO: implement readResolve() to preserve singleton on deserialization
}
*/