package io.github.lix3nn53.guardiansofadelia.guardian.element;

import org.bukkit.ChatColor;

public enum ElementType {
    FIRE,
    WATER,
    EARTH,
    AIR,
    LIGHTNING;

    public String getCustomName() {
        switch (this) {
            case FIRE:
                return getChatColor() + "Fire";
            case WATER:
                return getChatColor() + "Water";
            case EARTH:
                return getChatColor() + "Earth";
            case AIR:
                return getChatColor() + "Air";
            case LIGHTNING:
                return getChatColor() + "Lightning";
        }
        return "elementCustomName";
    }

    public ChatColor getChatColor() {
        switch (this) {
            case FIRE:
                return ChatColor.RED;
            case WATER:
                return ChatColor.BLUE;
            case EARTH:
                return ChatColor.DARK_GREEN;
            case AIR:
                return ChatColor.WHITE;
            case LIGHTNING:
                return ChatColor.DARK_PURPLE;
        }

        return null;
    }

    public Character getIcon() {
        switch (this) {
            case FIRE:
                return 'f';
            case WATER:
                return 'w';
            case EARTH:
                return 'e';
            case AIR:
                return 'a';
            case LIGHTNING:
                return 'l';
        }

        return null;
    }
}