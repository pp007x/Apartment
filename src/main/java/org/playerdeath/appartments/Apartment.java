package org.playerdeath.appartments;

public class Apartment {
    private final String regionId;
    private final double price;
    private String owner;

    public Apartment(String regionId, double price) {
        this.regionId = regionId;
        this.price = price;
        this.owner = null;
    }

    public String getRegionId() {
        return regionId;
    }

    public double getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

