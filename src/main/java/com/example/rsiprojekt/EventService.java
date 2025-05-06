package com.example.rsiprojekt;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.ws.soap.MTOM;

import java.io.File;
import java.util.*;

@WebService
@MTOM
public class EventService {

    private static final Map<String, Event> events = new HashMap<>();

    @WebMethod
    public String addEvent(Event e) {
        events.put(e.name, e);
        return "Dodano wydarzenie: " + e.name;
    }

    @WebMethod
    public Event getEventDetails(String name) {
        return events.get(name);
    }

    @WebMethod
    public List<Event> getEventsForDay(String date) {
        List<Event> result = new ArrayList<>();
        for (Event e : events.values()) {
            if (e.date.equals(date)) result.add(e);
        }
        return result;
    }

    @WebMethod
    public List<Event> getEventsForWeek(int year, int week) {
        List<Event> result = new ArrayList<>();
        for (Event e : events.values()) {
            if (e.week == week && e.year == year) result.add(e);
        }
        return result;
    }

    @WebMethod
    public String updateEvent(Event e) {
        events.put(e.name, e);
        return "Zaktualizowano wydarzenie: " + e.name;
    }

    @WebMethod
    public DataHandler getEventsAsPDF() {
        File file = new File("event_report.pdf");
        // <- tu generujesz plik PDF (później dodamy)
        DataSource source = new FileDataSource(file);
        return new DataHandler(source);
    }
}