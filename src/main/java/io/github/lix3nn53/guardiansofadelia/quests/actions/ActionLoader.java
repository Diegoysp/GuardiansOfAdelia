package io.github.lix3nn53.guardiansofadelia.quests.actions;

import io.github.lix3nn53.guardiansofadelia.GuardiansOfAdelia;
import io.github.lix3nn53.guardiansofadelia.items.GearLevel;
import io.github.lix3nn53.guardiansofadelia.items.RpgGears.ItemTier;
import io.github.lix3nn53.guardiansofadelia.items.config.ArmorReferenceData;
import io.github.lix3nn53.guardiansofadelia.items.config.ItemReferenceLoader;
import io.github.lix3nn53.guardiansofadelia.items.config.WeaponReferenceData;
import io.github.lix3nn53.guardiansofadelia.text.ChatPalette;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class ActionLoader {

    public static Action load(ConfigurationSection configurationSection) {
        String actionType = configurationSection.getString("actionType");

        if (actionType == null) {
            GuardiansOfAdelia.getInstance().getLogger().info(ChatPalette.RED + "NULL ACTION TYPE");
            return null;
        }

        if (actionType.equals(ClearPotionEffectAction.class.getSimpleName())) {
            String potionEffectTypeStr = configurationSection.getString("potionEffectType");
            PotionEffectType potionEffectType = PotionEffectType.getByName(potionEffectTypeStr);

            return new ClearPotionEffectAction(potionEffectType);
        } else if (actionType.equals(FinishQuestAction.class.getSimpleName())) {
            int questId = configurationSection.getInt("questId");
            boolean ignoreCompilation = configurationSection.contains("ignoreCompilation");
            if (ignoreCompilation) {
                ignoreCompilation = configurationSection.getBoolean("ignoreCompilation");
            }

            return new FinishQuestAction(questId, ignoreCompilation);
        } else if (actionType.equals(GiveItemAction.class.getSimpleName())) {
            ItemStack itemStack = ItemReferenceLoader.loadItemReference(configurationSection.getConfigurationSection("item"));

            return new GiveItemAction(itemStack);
        } else if (actionType.equals(GiveWeaponAction.class.getSimpleName())) {
            GearLevel gearLevel = GearLevel.values()[configurationSection.getInt("gearLevel")];
            ItemTier itemTier = ItemTier.valueOf(configurationSection.getString("itemTier"));
            int itemIndex = configurationSection.getInt("itemIndex");

            return new GiveWeaponAction(gearLevel, itemTier, itemIndex);
        } else if (actionType.equals(InvincibleGiveAction.class.getSimpleName())) {
            long duration = configurationSection.getLong("duration");

            return new InvincibleGiveAction(duration);
        } else if (actionType.equals(PotionEffectAction.class.getSimpleName())) {
            String potionEffectTypeStr = configurationSection.getString("potionEffectType");
            PotionEffectType potionEffectType = PotionEffectType.getByName(potionEffectTypeStr);

            int duration = configurationSection.getInt("duration");
            int amplifier = configurationSection.getInt("amplifier");

            return new PotionEffectAction(potionEffectType, duration, amplifier);
        } else if (actionType.equals(SendMessageAction.class.getSimpleName())) {
            String message = configurationSection.getString("message");

            return new SendMessageAction(message);
        } else if (actionType.equals(SendTitleAction.class.getSimpleName())) {
            String top = configurationSection.getString("top");
            String bottom = configurationSection.getString("bottom");

            return new SendTitleAction(top, bottom);
        } else if (actionType.equals(StartQuestAction.class.getSimpleName())) {
            int questId = configurationSection.getInt("questId");

            return new StartQuestAction(questId);
        } else if (actionType.equals(TeleportAction.class.getSimpleName())) {
            World world = Bukkit.getWorld(configurationSection.getString("world"));

            float x = (float) configurationSection.getDouble(".x");
            float y = (float) configurationSection.getDouble(".y");
            float z = (float) configurationSection.getDouble(".z");
            float yaw = (float) (float) configurationSection.getDouble(".yaw");
            float pitch = (float) (float) configurationSection.getDouble(".pitch");

            Location location = new Location(world, x, y, z, yaw, pitch);

            long delay = configurationSection.getLong("delay");

            return new TeleportAction(location, delay);
        } else if (actionType.equals(TutorialEndAction.class.getSimpleName())) {
            return new TutorialEndAction();
        } else if (actionType.equals(WeaponSelectOneOfAction.class.getSimpleName())) {
            WeaponReferenceData weaponReferenceData = new WeaponReferenceData(configurationSection);
            return new WeaponSelectOneOfAction(weaponReferenceData);
        } else if (actionType.equals(ArmorSelectOneOfAction.class.getSimpleName())) {
            ArmorReferenceData armorReferenceData = new ArmorReferenceData(configurationSection);
            return new ArmorSelectOneOfAction(armorReferenceData);
        } else if (actionType.equals(DoNotGetAway.class.getSimpleName())) {
            World world = Bukkit.getWorld(configurationSection.getString("world"));

            float x = (float) configurationSection.getDouble(".x");
            float y = (float) configurationSection.getDouble(".y");
            float z = (float) configurationSection.getDouble(".z");
            float yaw = (float) configurationSection.getDouble(".yaw");
            float pitch = (float) configurationSection.getDouble(".pitch");

            Location center = new Location(world, x, y, z, yaw, pitch);

            float distance = (float) configurationSection.getDouble(".distance");
            String onLeave = configurationSection.getString(".onLeave");
            return new DoNotGetAway(center, distance, onLeave);
        } else if (actionType.equals(DoNotGetAwayClear.class.getSimpleName())) {
            return new DoNotGetAwayClear();
        } else if (actionType.equals(StartAutoTrack.class.getSimpleName())) {
            return new StartAutoTrack();
        }

        GuardiansOfAdelia.getInstance().getLogger().info(ChatPalette.RED + "NO SUCH ACTION IN LOADER: " + configurationSection.getCurrentPath());

        return null;
    }
}
