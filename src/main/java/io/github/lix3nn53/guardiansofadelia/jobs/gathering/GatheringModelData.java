package io.github.lix3nn53.guardiansofadelia.jobs.gathering;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.List;


public class GatheringModelData {

    // Model data
    private final int customModelData;
    private final int cooldownCustomModelData;
    private final String title;
    private final Material material;
    private final boolean disguise;

    // Gathering data
    private final List<Integer> ingredients;
    private final GatheringToolType gatheringToolType;
    private final GatheringToolTier minGatheringToolTier;

    public GatheringModelData(int customModelData, int cooldownCustomModelData, String title, Material material,
                              boolean disguise, List<Integer> ingredients, GatheringToolType gatheringToolType, GatheringToolTier minGatheringToolTier) {
        this.customModelData = customModelData;
        this.cooldownCustomModelData = cooldownCustomModelData;
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.material = material;
        this.disguise = disguise;
        this.ingredients = ingredients;
        this.gatheringToolType = gatheringToolType;
        this.minGatheringToolTier = minGatheringToolTier;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public int getCooldownCustomModelData() {
        return cooldownCustomModelData;
    }

    public String getTitle() {
        return title;
    }

    public Material getMaterial() {
        return material;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public GatheringToolType getGatheringToolType() {
        return gatheringToolType;
    }

    public GatheringToolTier getMinGatheringToolTier() {
        return minGatheringToolTier;
    }

    public boolean isDisguise() {
        return disguise;
    }
}
