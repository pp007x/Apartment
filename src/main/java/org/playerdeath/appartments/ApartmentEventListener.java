import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.playerdeath.appartments.Apartment;
import org.playerdeath.appartments.ApartmentManager;
package org.playerdeath.appartments;
public class ApartmentEventListener implements Listener {
    private final ApartmentManager apartmentManager;

    public ApartmentEventListener(ApartmentManager apartmentManager) {
        this.apartmentManager = apartmentManager;
    }

    @EventHandler
    public void onDoorInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.OAK_DOOR) { // Check for the door types you want
                Location location = block.getLocation();
                RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
                RegionQuery query = container.createQuery();

                LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
                ProtectedRegion region = query.getApplicableRegions(location).getRegions().stream()
                        .filter(r -> r.isMember(localPlayer))
                        .findFirst()
                        .orElse(null);

                if (region != null) {
                    Apartment apartment = apartmentManager.getApartment(region.getId());
                    if (apartment != null) {
                        if (apartment.getOwner() == null) {
                            event.getPlayer().sendMessage("This apartment costs " + apartment.getPrice() + " to rent.");
                        } else {
                            event.getPlayer().sendMessage("This apartment is owned by " + apartment.getOwner() + ".");
                        }
                    }
                }
            }
        }
    }
}
