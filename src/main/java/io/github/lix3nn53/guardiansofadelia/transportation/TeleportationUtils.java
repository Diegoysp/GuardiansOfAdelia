package io.github.lix3nn53.guardiansofadelia.transportation;

import io.github.lix3nn53.guardiansofadelia.GuardiansOfAdelia;
import io.github.lix3nn53.guardiansofadelia.economy.EconomyUtils;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianData;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianDataManager;
import io.github.lix3nn53.guardiansofadelia.text.ChatPalette;
import io.github.lix3nn53.guardiansofadelia.utilities.InventoryUtils;
import io.github.lix3nn53.guardiansofadelia.utilities.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportationUtils {

    public static void teleport(Player player, Location location, String destination, int stepCount, ItemStack itemCost, int cost) {
        if (GuardianDataManager.hasGuardianData(player)) {
            GuardianData guardianData = GuardianDataManager.getGuardianData(player);
            if (guardianData.isFreeToAct()) {
                guardianData.setTeleporting(true);
                final float startPosX = (float) player.getLocation().getX();
                final float startPosY = (float) player.getLocation().getY();
                final float startPosZ = (float) player.getLocation().getZ();

                ArmorStand hologramTop = new Hologram(player.getLocation().add(0.0, 2.6, 0.0),
                        ChatPalette.BLUE + "< " + ChatPalette.YELLOW + destination + ChatPalette.BLUE + " >").getArmorStand();
                ArmorStand hologramBottom = new Hologram(player.getLocation().add(0.0, 2.3, 0.0),
                        ChatPalette.BLUE_LIGHT + "Teleporting.. " + stepCount).getArmorStand();
                player.sendTitle(ChatPalette.BLUE + "Teleporting..", ChatPalette.BLUE_LIGHT.toString() + stepCount, 5, 20, 5);

                new BukkitRunnable() {

                    // We don't want the task to run indefinitely
                    int ticksRun;

                    @Override
                    public void run() {
                        ticksRun++;

                        boolean doesDivide = ticksRun % 4 == 0;
                        if (doesDivide) {
                            int currentStep = ticksRun / 4;

                            float differenceX = Math.abs(startPosX - (float) player.getLocation().getX());
                            float differenceY = Math.abs(startPosY - (float) player.getLocation().getY());
                            float differenceZ = Math.abs(startPosZ - (float) player.getLocation().getZ());

                            if (currentStep < stepCount) {
                                if (isTeleportCanceled(differenceX, differenceY, differenceZ)) {
                                    cancelTeleportation(this, guardianData, hologramTop, hologramBottom, player);
                                } else {
                                    nextStep(player, hologramTop, hologramBottom, destination, stepCount - currentStep);
                                }
                            } else {
                                if (isTeleportCanceled(differenceX, differenceY, differenceZ)) {
                                    cancelTeleportation(this, guardianData, hologramTop, hologramBottom, player);
                                } else {
                                    finishTeleportation(this, guardianData, hologramTop, hologramBottom,
                                            player, location, destination, itemCost, cost);
                                }
                            }
                        }
                    }
                }.runTaskTimer(GuardiansOfAdelia.getInstance(), 5L, 5L);
            }
        }
    }

    public static boolean isTeleportCanceled(float differenceX, float differenceY, float differenceZ) {
        return differenceX > 1 || differenceY > 1 || differenceZ > 1;
    }

    public static void cancelTeleportation(BukkitRunnable runnable, GuardianData guardianData,
                                           ArmorStand hologramTop, ArmorStand hologramBottom, Player player) {
        runnable.cancel();
        guardianData.setTeleporting(false);
        hologramTop.remove();
        hologramBottom.remove();
        player.sendMessage(ChatPalette.RED + "Teleportation has been canceled because you moved.");
    }

    public static void nextStep(Player player, ArmorStand hologramTop, ArmorStand hologramBottom, String destination, int countDown) {
        player.sendTitle(ChatPalette.BLUE + "Teleporting..", ChatPalette.BLUE_LIGHT.toString() + countDown, 5, 20, 5);
        hologramTop.setCustomName(ChatPalette.BLUE + "< " + ChatPalette.YELLOW + destination + ChatPalette.BLUE + " >");
        hologramBottom.setCustomName(ChatPalette.BLUE_LIGHT + "Teleporting.. " + countDown);
    }

    public static void finishTeleportation(BukkitRunnable runnable, GuardianData guardianData,
                                           ArmorStand hologramTop, ArmorStand hologramBottom, Player player,
                                           Location location, String destination, ItemStack itemCost, int cost) {
        if (itemCost != null) {
            boolean b = InventoryUtils.removeItemFromInventory(player.getInventory(), itemCost, 1);

            if (!b) {
                player.sendMessage(ChatPalette.RED + "Teleportation has been canceled because you don't have required item.");
                return;
            }
        }

        if (cost > 0) {
            boolean b = EconomyUtils.pay(player, cost);

            if (!b) {
                player.sendMessage(ChatPalette.RED + "Teleportation has been canceled because you don't have enough coins.");
                return;
            }
        }

        runnable.cancel();
        guardianData.setTeleporting(false);
        hologramTop.remove();
        hologramBottom.remove();
        player.teleport(location);
        player.sendTitle(ChatPalette.YELLOW + destination, ChatPalette.BLUE + "Teleported!", 20, 40, 20);
    }
}
