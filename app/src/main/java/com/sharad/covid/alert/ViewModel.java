package com.sharad.covid.alert;

public class ViewModel {
    private String active;
    private String confirmed;
    private String deaths;
    private String recovered;
    private String state;
    private String lastupdatedtime, deltaconfirmed, deltadeaths, deltarecovered;

    public ViewModel() {

    }

    public ViewModel(String active, String confirmed, String deaths, String recovered, String state, String lastupdatedtime, String deltaconfirmed, String deltadeaths, String deltarecovered) {
        this.active = active;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.state = state;
        this.lastupdatedtime = lastupdatedtime;
        this.deltaconfirmed = deltaconfirmed;
        this.deltadeaths = deltadeaths;
        this.deltarecovered = deltarecovered;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public void setDeltaconfirmed(String deltaconfirmed) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public void setDeltadeaths(String deltadeaths) {
        this.deltadeaths = deltadeaths;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public void setDeltarecovered(String deltarecovered) {
        this.deltarecovered = deltarecovered;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
