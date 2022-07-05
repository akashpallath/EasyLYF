package com.example.easylyf.models;

public class ModelShop {
    private String uid, name, mobileno, shopname, deliveryfee, address, email, timestamp, accountType, online, shopOpen;

    public ModelShop(){

    }

    public ModelShop(String uid, String name, String mobileno, String shopname, String deliveryfee, String address, String email, String timestamp, String accountType, String online, String shopOpen) {
        this.uid = uid;
        this.name = name;
        this.mobileno = mobileno;
        this.shopname = shopname;
        this.deliveryfee = deliveryfee;
        this.address = address;
        this.email = email;
        this.timestamp = timestamp;
        this.accountType = accountType;
        this.online = online;
        this.shopOpen = shopOpen;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getDeliveryfee() {
        return deliveryfee;
    }

    public void setDeliveryfee(String deliveryfee) {
        this.deliveryfee = deliveryfee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getShopOpen() {
        return shopOpen;
    }

    public void setShopOpen(String shopOpen) {
        this.shopOpen = shopOpen;
    }
}
