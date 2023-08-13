package org.playerdeath.appartments;

import jdk.tools.jlink.plugin.Plugin;

import java.util.HashMap;

public class ApartmentManager {
    private final Plugin plugin;
    private final Map<String, Apartment> apartments; // Region ID -> Apartment

    public ApartmentManager(Plugin plugin) {
        this.plugin = plugin;
        this.apartments = new HashMap<>();
    }

    public void createApartment(String regionId, double price) {
        apartments.put(regionId, new Apartment(regionId, price));
    }

    public Apartment getApartment(String regionId) {
        return apartments.get(regionId);
    }
}
