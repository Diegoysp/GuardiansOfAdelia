package io.github.lix3nn53.guardiansofadelia.events;

import io.github.lix3nn53.guardiansofadelia.creatures.pets.PetManager;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianData;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianDataManager;
import io.github.lix3nn53.guardiansofadelia.guardian.character.RPGCharacter;
import io.github.lix3nn53.guardiansofadelia.menu.MenuList;
import io.github.lix3nn53.guardiansofadelia.quests.Quest;
import io.github.lix3nn53.guardiansofadelia.rpginventory.slots.EggSlot;
import io.github.lix3nn53.guardiansofadelia.utilities.PersistentDataContainerUtil;
import io.github.lix3nn53.guardiansofadelia.utilities.gui.GuiGeneric;
import io.github.lix3nn53.guardiansofadelia.utilities.particle.Direction;
import io.github.lix3nn53.guardiansofadelia.utilities.particle.ParticleArrangement;
import io.github.lix3nn53.guardiansofadelia.utilities.particle.ParticleUtil;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class MyPlayerInteractEntityEvent implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEventNormal(PlayerInteractEntityEvent event) {
        Entity rightClicked = event.getRightClicked();
        if (rightClicked.getType().equals(EntityType.VILLAGER)) {
            NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
            if (!npcRegistry.isNPC(rightClicked)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void onEvent(PlayerInteractEntityEvent event) {
        if (!event.getHand().equals(EquipmentSlot.HAND)) {
            return;
        }

        Entity rightClicked = event.getRightClicked();
        Player player = event.getPlayer();

        if (player.isOp()) {
            if (rightClicked instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) rightClicked;

                String customName = livingEntity.getCustomName();
                player.sendMessage("customName: " + customName);

                double damage = livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
                double health = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                player.sendMessage("damage: " + damage);
                player.sendMessage("health: " + health);
            }
        }

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInMainHand.hasItemMeta()) {
            ItemMeta itemMeta = itemInMainHand.getItemMeta();
            if (itemMeta.hasDisplayName()) {
                if (itemInMainHand.getType().equals(Material.LAPIS_LAZULI)) { //right click with pet-food
                    String displayName = itemMeta.getDisplayName();
                    LivingEntity livingEntity = (LivingEntity) rightClicked;
                    if (PetManager.isPet(livingEntity)) {
                        AttributeInstance attribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        double maxHealth = attribute.getValue();
                        double currentHealth = livingEntity.getHealth();
                        if (currentHealth < maxHealth) {
                            int healAmount = 100;
                            if (displayName.contains("2")) {
                                healAmount = 200;
                            } else if (displayName.contains("3")) {
                                healAmount = 400;
                            } else if (displayName.contains("4")) {
                                healAmount = 800;
                            } else if (displayName.contains("5")) {
                                healAmount = 1200;
                            }
                            double setHealthAmount = currentHealth + healAmount;
                            if (setHealthAmount > maxHealth) {
                                setHealthAmount = maxHealth;
                            }
                            livingEntity.setHealth(setHealthAmount);
                            int setHealthInt = (int) (setHealthAmount + 0.5);
                            PetManager.onPetSetHealth(livingEntity, currentHealth, setHealthInt);
                            int amount = itemInMainHand.getAmount();
                            itemInMainHand.setAmount(amount - 1);
                            ParticleUtil.play(livingEntity.getLocation().add(0, 1.2, 0), Particle.HEART, ParticleArrangement.CIRCLE, 1.2, 6, Direction.XZ, 0, 0, 0, 0, null);
                        } else {
                            player.sendMessage(ChatColor.RED + "Pet health is already full");
                        }
                    }
                } else if (itemInMainHand.getType().equals(Material.BLACK_DYE)) { //right click with premium item
                    LivingEntity pet = (LivingEntity) rightClicked;
                    if (PetManager.isPet(pet)) {
                        if (!PersistentDataContainerUtil.hasString(itemInMainHand, "petSkinCode")) return;

                        if (!PetManager.isPet(pet)) return;

                        Player owner = PetManager.getOwner(pet);
                        if (!player.equals(owner)) return;

                        UUID uuid = player.getUniqueId();
                        if (GuardianDataManager.hasGuardianData(uuid)) {
                            GuardianData guardianData = GuardianDataManager.getGuardianData(uuid);
                            if (guardianData.hasActiveCharacter()) {
                                RPGCharacter activeCharacter = guardianData.getActiveCharacter();

                                EggSlot eggSlot = activeCharacter.getRpgInventory().getEggSlot();

                                if (eggSlot.isEmpty()) return;

                                ItemStack itemOnSlot = eggSlot.getItemOnSlot();

                                String petSkinCode = PersistentDataContainerUtil.getString(itemInMainHand, "petSkinCode");
                                PersistentDataContainerUtil.putString("petCode", petSkinCode, itemOnSlot);

                                //change values of itemOnSlot
                                ItemMeta onSlotItemMeta = itemOnSlot.getItemMeta();
                                String name = PetManager.getName(petSkinCode);
                                onSlotItemMeta.setDisplayName(name);
                                if (itemMeta.hasCustomModelData())
                                    onSlotItemMeta.setCustomModelData(itemMeta.getCustomModelData());
                                itemOnSlot.setItemMeta(onSlotItemMeta);

                                PetManager.respawnPet(owner);

                                int amount = itemInMainHand.getAmount();
                                itemInMainHand.setAmount(amount - 1);
                            }
                        }
                    } else if (PersistentDataContainerUtil.hasString(itemInMainHand, "boostCode")) {
                        //TODO boostcode? whats this here
                    }
                } else if (rightClicked.isCustomNameVisible()) {
                    UUID uniqueId = player.getUniqueId();
                    if (GuardianDataManager.hasGuardianData(uniqueId)) {
                        GuardianData guardianData = GuardianDataManager.getGuardianData(uniqueId);
                        if (guardianData.hasActiveCharacter()) {
                            RPGCharacter activeCharacter = guardianData.getActiveCharacter();
                            List<Quest> questList = activeCharacter.getQuestList();
                            for (Quest quest : questList) {
                                quest.progressGiftTasks(player, itemMeta.getDisplayName(), itemInMainHand.getAmount(), rightClicked.getCustomName());
                            }
                        }
                    }
                }
            }
        }

        if (rightClicked instanceof Player) {
            if (player.isSneaking()) {
                Player rightClickedPlayer = (Player) rightClicked;

                NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
                if (npcRegistry.isNPC(rightClickedPlayer)) return;

                if (!player.getGameMode().equals(GameMode.SPECTATOR) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
                    GuiGeneric guiGeneric = MenuList.onShiftRightClickPlayer(rightClickedPlayer);
                    guiGeneric.openInventory(player);
                }
            }
        }
    }
}
