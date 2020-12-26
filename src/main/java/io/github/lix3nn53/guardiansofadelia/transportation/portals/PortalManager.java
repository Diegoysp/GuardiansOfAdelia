package io.github.lix3nn53.guardiansofadelia.transportation.portals;

import io.github.lix3nn53.guardiansofadelia.utilities.LocationUtils;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PortalManager {

    private static final HashMap<String, List<Portal>> chunkKeyToPortal = new HashMap<>();
    private static final HashMap<Portal, InstantTeleportPortal> portalToLocation = new HashMap<>();

    public static void onChunkLoad(String chunkKey) {
        if (chunkKeyToPortal.containsKey(chunkKey)) {
            List<Portal> portals = chunkKeyToPortal.get(chunkKey);
            for (Portal portal : portals) {
                portal.createModel();
            }
        }
    }

    public static void removePortal(Portal portal) {
        String chunkKey = LocationUtils.getChunkKey(portal.getBaseLocation());
        if (chunkKeyToPortal.containsKey(chunkKey)) {
            List<Portal> portals = chunkKeyToPortal.get(chunkKey);
            portals.remove(portal);
            if (portals.isEmpty()) {
                chunkKeyToPortal.remove(chunkKey);
            } else {
                chunkKeyToPortal.put(chunkKey, portals);
            }
        }
    }

    public static void addPortal(Portal portal) {
        String chunkKey = LocationUtils.getChunkKey(portal.getBaseLocation());
        if (chunkKeyToPortal.containsKey(chunkKey)) {
            List<Portal> portals = chunkKeyToPortal.get(chunkKey);
            portals.add(portal);
            chunkKeyToPortal.put(chunkKey, portals);
        } else {
            List<Portal> portals = new ArrayList<>();
            portals.add(portal);
            chunkKeyToPortal.put(chunkKey, portals);
        }
    }

    public static Portal getPortalFromArmorStand(ArmorStand armorStand) {
        for (String key : chunkKeyToPortal.keySet()) {
            List<Portal> portals = chunkKeyToPortal.get(key);
            for (Portal portal : portals) {
                if (portal.getArmorStand() != null) {
                    if (portal.getArmorStand().equals(armorStand)) {
                        return portal;
                    }
                }
            }
        }
        return null;
    }

    public static void addInstantTeleportPortal(Portal portal, InstantTeleportPortal instantTeleportPortal) {
        portalToLocation.put(portal, instantTeleportPortal);
    }

    public static boolean isInstantTeleportPortal(Portal portal) {
        return portalToLocation.containsKey(portal);
    }

    public static InstantTeleportPortal getInstantTeleportPortal(Portal portal) {
        return portalToLocation.get(portal);
    }
}
