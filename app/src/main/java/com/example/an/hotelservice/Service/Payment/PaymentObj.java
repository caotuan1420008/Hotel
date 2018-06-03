package com.example.an.hotelservice.Service.Payment;

public class PaymentObj {
    private String title, notice, money;


    public PaymentObj() {
    }

    public PaymentObj(String title, String notice, String money) {
        this.title = title;
        this.notice = notice;
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}