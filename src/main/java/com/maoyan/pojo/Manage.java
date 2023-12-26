package com.maoyan.pojo;

public class Manage {
    private long schedule_id;
    private String cinema_name;
    private String cinema_address;
    private String hall_name;
    private String movie_cn_name;
    private String schedule_startTime;
    private int schedule_price; //售价

    @Override
    public String toString() {
        return "Manage{" +
                "schedule_id=" + schedule_id +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_address='" + cinema_address + '\'' +
                ", hall_name='" + hall_name + '\'' +
                ", movie_detail='" + movie_cn_name + '\'' +
                ", schedule_startTime='" + schedule_startTime + '\'' +
                ", schedule_price=" + schedule_price +
                '}';
    }

    public Manage() {
    }

    public Manage(long schedule_id, String cinema_name, String cinema_address, String hall_name, String movie_detail, String schedule_startTime, int schedule_price) {
        this.schedule_id = schedule_id;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
        this.hall_name = hall_name;
        this.movie_cn_name = movie_detail;
        this.schedule_startTime = schedule_startTime;
        this.schedule_price = schedule_price;
    }

    public long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public String getMovie_detail() {
        return movie_cn_name;
    }

    public void setMovie_detail(String movie_detail) {
        this.movie_cn_name = movie_detail;
    }

    public String getSchedule_startTime() {
        return schedule_startTime;
    }

    public void setSchedule_startTime(String schedule_startTime) {
        this.schedule_startTime = schedule_startTime;
    }

    public int getSchedule_price() {
        return schedule_price;
    }

    public void setSchedule_price(int schedule_price) {
        this.schedule_price = schedule_price;
    }
}
