package com.MotherSon.CRM.dto;

public class AggregateCountDTO {
	
	private long totalQueryBook;
    private long activeQueryBook;
    private long totalUser;
    private long activeUser;
    private long totalCustomer;
    private long activeCustomer;
    private long totalBookings;
    private long activeBookings;

    // Getters and Setters

    public long getTotalQueryBook() {
        return totalQueryBook;
    }

    public void setTotalQueryBook(long totalQueryBook) {
        this.totalQueryBook = totalQueryBook;
    }

    public long getActiveQueryBook() {
        return activeQueryBook;
    }

    public void setActiveQueryBook(long activeQueryBook) {
        this.activeQueryBook = activeQueryBook;
    }

    public long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(long totalUser) {
        this.totalUser = totalUser;
    }

    public long getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(long activeUser) {
        this.activeUser = activeUser;
    }

    public long getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(long totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public long getActiveCustomer() {
        return activeCustomer;
    }

    public void setActiveCustomer(long activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getActiveBookings() {
        return activeBookings;
    }

    public void setActiveBookings(long activeBookings) {
        this.activeBookings = activeBookings;
    }
}


