package org.playerdeath.appartments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ApartmentCommands implements CommandExecutor {
    private final ApartmentManager manager;

    public ApartmentCommands(ApartmentManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createApartment")) {
            if (sender.hasPermission("apartment.create")) {
                if (args.length == 2) {
                    String regionId = args[0];
                    double price = Double.parseDouble(args[1]);
                    manager.createApartment(regionId, price);
                    sender.sendMessage("Apartment created!");
                    return true;
                } else {
                    sender.sendMessage("Usage: /createApartment <regionId> <price>");
                }
            } else {
                sender.sendMessage("You don't have permission to do that!");
            }
        }
        return false;
    }
}
