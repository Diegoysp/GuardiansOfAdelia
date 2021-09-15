package io.github.lix3nn53.guardiansofadelia.events;

import io.github.lix3nn53.guardiansofadelia.items.list.OtherItems;
import io.github.lix3nn53.guardiansofadelia.utilities.InventoryUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;

public class MyVehicleDestroyEvent implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEvent(VehicleDestroyEvent event) {
        Vehicle vehicle = event.getVehicle();

        if (vehicle.getType().equals(EntityType.BOAT)) {
            ItemStack boat = OtherItems.getBoat();

            if (event.getAttacker() instanceof Player) {
                Player player = (Player) event.getAttacker();

                InventoryUtils.giveItemToPlayer(player, boat);
            } else {
                Item item = vehicle.getWorld().dropItemNaturally(vehicle.getLocation(), boat);
            }
        }
    }
}
