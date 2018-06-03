package com.example.an.hotelservice.Room;

public class Room {
    int id;
    String name;
    double price;
    String description;
    String overview;
    int Wifi;
    int aircon;
    int dayFrom;
    int dayTo;
    int monthFrom;
    int monthTo;
    int bed;
    String img1;
    String img2;

    String thumbnail;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }
    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getWifi() {
        return Wifi;
    }

    public void setWifi(int Wifi) {
        this.Wifi = Wifi;
    }
    public int getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(int dayFrom) {
        this.dayFrom = dayFrom;
    }
    public int getAircon() {
        return aircon;
    }

    public void setAircon(int aircon) {
        this.aircon = aircon;
    }
    public int getDayTo() {
        return dayTo;
    }

    public void setDayTo(int dayTo) {
        this.dayTo = dayTo;
    }

    public int getMonthFrom() {
        return monthFrom;
    }

    public void setMonthFrom(int monthFrom) {
        this.monthFrom = monthFrom;
    }
    public int getMonthTo() {
        return monthTo;
    }

    public void setMonthTo(int monthTo) {
        this.monthTo = monthTo;
    }
    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

}