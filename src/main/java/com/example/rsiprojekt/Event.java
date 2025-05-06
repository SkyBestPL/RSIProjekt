package com.example.rsiprojekt;

import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlType
public class Event {
    public String id;
    public String name;
    public String type;
    public String date;
    public int week;
    public int month;
    public int year;
    public String description;

    public Event() {
        this.id = UUID.randomUUID().toString();
    }
}