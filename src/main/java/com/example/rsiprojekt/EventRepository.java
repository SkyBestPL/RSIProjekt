package com.example.rsiprojekt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EventRepository {
    private static final String FILE_NAME = "events.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static synchronized void saveEvents(Map<String, Event> events) {
        try {
            mapper.writeValue(new File(FILE_NAME), events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Map<String, Event> loadEvents() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return new HashMap<>();
            return mapper.readValue(file, mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Event.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
