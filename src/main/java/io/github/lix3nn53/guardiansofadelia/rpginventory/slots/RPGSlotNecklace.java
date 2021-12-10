package io.github.lix3nn53.guardiansofadelia.rpginventory.slots;

import io.github.lix3nn53.guardiansofadelia.text.ChatPalette;
import io.github.lix3nn53.guardiansofadelia.utilities.PersistentDataContainerUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RPGSlotNecklace extends RPGSlotPassive implements RPGSlot {

    private final RPGSlotType passiveType = RPGSlotType.NECKLACE;

    public boolean doesFit(ItemStack itemStack) {
        if (PersistentDataContainerUtil.hasString(itemStack, "passive")) {
            String typeStr = PersistentDataContainerUtil.getString(itemStack, "passive");
            return RPGSlotType.valueOf(typeStr).equals(this.passiveType);
        }
        return false;
    }

    public ItemStack getFillItem() {
        ItemStack itemStack = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setDisplayName(ChatPalette.YELLOW + "Necklace Slot");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setCustomModelData(11);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
