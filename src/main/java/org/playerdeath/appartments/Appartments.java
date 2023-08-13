package org.playerdeath.appartments;

import org.bukkit.plugin.java.JavaPlugin;

public class Appartments extends JavaPlugin {

    private ApartmentManager apartmentManager;

    @Override
    public void onEnable() {
        // Initialize the apartment manager
        apartmentManager = new ApartmentManager(this);

        // Register commands
        ApartmentCommands apartmentCommands = new ApartmentCommands(apartmentManager);
        getCommand("createApartment").setExecutor(apartmentCommands);

        // Register event listeners
        getServer().getPluginManager().registerEvents(new ApartmentEventListener(apartmentManager), this);

        getLogger().info("ApartmentPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ApartmentPlugin has been disabled!");
    }

    public ApartmentManager getApartmentManager() {
        return apartmentManager;
    }
}

