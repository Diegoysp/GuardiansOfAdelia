package io.github.lix3nn53.guardiansofadelia.rpginventory.slots;

import io.github.lix3nn53.guardiansofadelia.Items.stats.StatUtils;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianData;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianDataManager;
import io.github.lix3nn53.guardiansofadelia.guardian.character.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class CharacterInfoSlot {

    private final Player player;

    public CharacterInfoSlot(Player player) {
        this.player = player;
    }

    public ItemStack getItem() {
        UUID uuid = player.getUniqueId();
        if (GuardianDataManager.hasGuardianData(uuid)) {
            GuardianData guardianData = GuardianDataManager.getGuardianData(uuid);
            if (guardianData.hasActiveCharacter()) {
                RPGCharacter activeCharacter = guardianData.getActiveCharacter();
                RPGCharacterStats rpgCharacterStats = activeCharacter.getRpgCharacterStats();

                ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
                skullMeta.setDisplayName(ChatColor.YELLOW + "Character Info");

                int charNo = guardianData.getActiveCharacterNo();
                String rpgClassStr = activeCharacter.getRpgClassStr();
                RPGClass rpgClass = RPGClassManager.getClass(rpgClassStr);
                String className = rpgClass.getClassString();
                int mana = rpgCharacterStats.getCurrentMana();
                int maxMana = rpgCharacterStats.getTotalMaxMana();
                final int level = player.getLevel();
                int totalExp = rpgCharacterStats.getTotalExp();
                int exp = RPGCharacterExperienceManager.getCurrentExperience(totalExp, level);
                int expReq = RPGCharacterExperienceManager.getRequiredExperience(level);
                int maxHealth = (int) (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + 0.5);
                int health = (int) (player.getHealth() + 0.5);
                double criticalChance = rpgCharacterStats.getTotalCriticalChance() * 100;

                final io.github.lix3nn53.guardiansofadelia.guardian.attribute.Attribute fire = rpgCharacterStats.getFire();
                final io.github.lix3nn53.guardiansofadelia.guardian.attribute.Attribute water = rpgCharacterStats.getWater();
                final io.github.lix3nn53.guardiansofadelia.guardian.attribute.Attribute earth = rpgCharacterStats.getEarth();
                final io.github.lix3nn53.guardiansofadelia.guardian.attribute.Attribute lightning = rpgCharacterStats.getLightning();
                final io.github.lix3nn53.guardiansofadelia.guardian.attribute.Attribute wind = rpgCharacterStats.getWind();

                final int totalDefense = rpgCharacterStats.getTotalDefense();
                double phyReduction = StatUtils.getDefenseReduction(totalDefense);

                final int totalMagicDefense = rpgCharacterStats.getTotalMagicDefense();
                double mgcReduction = StatUtils.getDefenseReduction(totalMagicDefense);

                skullMeta.setLore(new ArrayList() {{
                    add("");
                    add(ChatColor.LIGHT_PURPLE + "Class: " + ChatColor.GRAY + "" + className);
                    add(ChatColor.GOLD + "Level: " + ChatColor.GRAY + "" + level);
                    add(ChatColor.YELLOW + "Experience: " + ChatColor.GRAY + exp + "/" + expReq);
                    add(ChatColor.GREEN + "❤ Health: " + ChatColor.GRAY + "" + health + "/" + maxHealth);
                    add(ChatColor.AQUA + "✧ Mana: " + ChatColor.GRAY + "" + mana + "/" + maxMana);
                    add("");
                    add(ChatColor.RED + "⸸ Damage: " + ChatColor.GRAY + rpgCharacterStats.getTotalMeleeDamage(player, rpgClassStr));
                    add(ChatColor.RED + "➹ Ranged Damage: " + ChatColor.GRAY + rpgCharacterStats.getTotalRangedDamage(player, rpgClassStr));
                    add(ChatColor.DARK_AQUA + "✦ Magic Damage: " + ChatColor.GRAY + rpgCharacterStats.getTotalMagicDamage(player, rpgClassStr));
                    add(ChatColor.AQUA + "■ Defense: " + ChatColor.GRAY + totalDefense + " (" + (int) (((1.0 - phyReduction) * 100) + 0.5) + "% reduction)");
                    add(ChatColor.BLUE + "✦ Magic Defense: " + ChatColor.GRAY + totalMagicDefense + " (" + (int) (((1.0 - mgcReduction) * 100) + 0.5) + "% reduction)");
                    add(ChatColor.GOLD + "⚝ Critical chance: " + ChatColor.GRAY + criticalChance + "%");
                    add("");
                    add(ChatColor.GRAY + "(equipment + level + invested points)");
                    add(ChatColor.RED + "☄" + ChatColor.RED + " Fire: " + ChatColor.GRAY + fire.getBonusFromEquipment() + " + " + fire.getBonusFromLevel(level, rpgClassStr) + " + " + fire.getInvested());
                    add(ChatColor.BLUE + "◎ " + ChatColor.BLUE + "Water: " + ChatColor.GRAY + water.getBonusFromEquipment() + " + " + water.getBonusFromLevel(level, rpgClassStr) + " + " + water.getInvested());
                    add(ChatColor.DARK_GREEN + "₪ " + ChatColor.DARK_GREEN + "Earth: " + ChatColor.GRAY + earth.getBonusFromEquipment() + " + " + earth.getBonusFromLevel(level, rpgClassStr) + " + " + earth.getInvested());
                    add(ChatColor.AQUA + "ϟ " + ChatColor.AQUA + "Lightning: " + ChatColor.GRAY + lightning.getBonusFromEquipment() + " + " + lightning.getBonusFromLevel(level, rpgClassStr) + " + " + lightning.getInvested());
                    add(ChatColor.WHITE + "๑ " + ChatColor.WHITE + "Wind: " + ChatColor.GRAY + wind.getBonusFromEquipment() + " + " + wind.getBonusFromLevel(level, rpgClassStr) + " + " + wind.getInvested());
                }});
                itemStack.setItemMeta(skullMeta);
                return itemStack;
            }
        }
        return new ItemStack(Material.AIR);
    }
}
