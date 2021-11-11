package io.github.lix3nn53.guardiansofadelia.utilities.config;

import io.github.lix3nn53.guardiansofadelia.transportation.portals.InstantTeleportPortal;
import io.github.lix3nn53.guardiansofadelia.transportation.portals.Portal;
import io.github.lix3nn53.guardiansofadelia.transportation.portals.PortalColor;
import io.github.lix3nn53.guardiansofadelia.transportation.portals.PortalManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class TeleportPortalsConfiguration {

    private static FileConfiguration fileConfiguration;
    private static final String filePath = ConfigManager.DATA_FOLDER + File.separator + "world";

    static void createConfig() {
        fileConfiguration = ConfigurationUtils.createConfig(filePath, "teleportPortals.yml");
    }

    static void loadConfig() {
        for (int i = 1; i <= 1000; i++) {
            boolean contains = fileConfiguration.contains("p" + i);
            if (!contains) break;
            ConfigurationSection current = fileConfiguration.getConfigurationSection("p" + i);

            String worldString = current.getString("world");
            World world = Bukkit.getWorld(worldString);
            float x = (float) current.getDouble("x");
            float y = (float) current.getDouble("y");
            float z = (float) current.getDouble("z");
            float yaw = (float) current.getDouble("yaw");
            float pitch = (float) current.getDouble("pitch");
            Location location = new Location(world, x, y, z, yaw, pitch);
            PortalColor portalColor = PortalColor.valueOf(current.getString("color"));
            String title = current.getString("title");
            Portal portal = new Portal(location, portalColor, title);

            PortalManager.addPortal(portal);

            String tpWorldString = current.getString("tpWorld");
            World tpWorld = Bukkit.getWorld(tpWorldString);
            float tpX = (float) current.getDouble("tpX");
            float tpY = (float) current.getDouble("tpY");
            float tpZ = (float) current.getDouble("tpZ");
            float tpYaw = (float) current.getDouble("tpYaw");
            float tpPitch = (float) current.getDouble("tpPitch");
            Location tpLocation = new Location(tpWorld, tpX, tpY, tpZ, tpYaw, tpPitch);

            int requiredQuestNoAccepted = current.getInt("requiredQuestNoAccepted");
            int requiredQuestNoTurnedIn = current.getInt("requiredQuestNoTurnedIn");

            InstantTeleportPortal instantTeleportPortal = new InstantTeleportPortal(tpLocation, requiredQuestNoAccepted, requiredQuestNoTurnedIn);
            PortalManager.addInstantTeleportPortal(portal, instantTeleportPortal);
        }
    }
}
