package com.example.rsiprojekt;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.ws.soap.MTOM;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@WebService
@MTOM
//@HandlerChain(file = "/handler-chain.xml")
public class EventService {

    private static final Map<String, Event> events = EventRepository.loadEvents();

    @WebMethod
    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    @WebMethod
    public String addEvent(Event e) {
        if (e.id == null || e.id.isEmpty()) {
            e.id = UUID.randomUUID().toString();
        }
        events.put(e.id, e);
        EventRepository.saveEvents(events);
        return "Dodano wydarzenie o ID: " + e.id;
    }

    @WebMethod
    public Event getEventById(String id) {
        return events.get(id);
    }

    @WebMethod
    public List<Event> getEventsByName(String name) {
        List<Event> result = new ArrayList<>();
        for (Event e : events.values()) {
            if (e.name.equals(name)) result.add(e);
        }
        return result;
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
    public List<Event> getEventsForMonth(int year, int month) {
        List<Event> result = new ArrayList<>();
        for (Event e : events.values()) {
            if (e.month == month && e.year == year) result.add(e);
        }
        return result;
    }

    @WebMethod
    public String updateEvent(Event e) {
        events.put(e.id, e);
        EventRepository.saveEvents(events);
        return "Zaktualizowano wydarzenie: " + e.name + " (" + e.id + ")";
    }

    @WebMethod
    public String deleteEvent(String id) {
        if (events.remove(id) != null) {
            EventRepository.saveEvents(events);
            return "Usunięto wydarzenie o id: " + id;
        } else {
            return "Nie znaleziono wydarzenia o id: " + id;
        }
    }

    /*@WebMethod
    public DataHandler getEventsAsPDF() {
        File file = new File("event_report.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            for (Event e : events.values()) {
                document.add(new Paragraph("Nazwa: " + e.name));
                document.add(new Paragraph("Typ: " + e.type));
                document.add(new Paragraph("Data: " + e.date));
                document.add(new Paragraph("Opis: " + e.description));
                document.add(new Paragraph("------------"));
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        DataSource source = new FileDataSource(file);
        return new DataHandler(source);
    }*/
}