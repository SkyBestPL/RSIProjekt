package com.example.rsiprojekt;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Event {
    public String name;
    public String type;
    public String date;
    public int week;
    public int month;
    public int year;
    public String description;

    public Event() {} // JAXB needs a default constructor
}