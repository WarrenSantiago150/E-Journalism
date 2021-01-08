package com.example.ejournalism;

public class editorial_helper {
    String entry,user;

    public editorial_helper() {
    }

    public editorial_helper(String entry, String user) {
        this.entry = entry;
        this.user = user;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
