package io.github.lix3nn53.guardiansofadelia.rpginventory.slots;

public enum RPGSlotType {
    PARROT,
    EARRING,
    NECKLACE,
    GLOVE,
    RING,
    CHARACTER_INFO,
    PET,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    OFFHAND,
    MAINHAND,
    HOE,
    PICKAXE,
    AXE,
    BOTTLE;

    public int getSlotNo() {
        if (this == RPGSlotType.PARROT) {
            return 11;
        } else if (this == RPGSlotType.EARRING) {
            return 15;
        } else if (this == RPGSlotType.NECKLACE) {
            return 19;
        } else if (this == RPGSlotType.GLOVE) {
            return 23;
        } else if (this == RPGSlotType.RING) {
            return 28;
        } else if (this == RPGSlotType.CHARACTER_INFO) {
            return 14;
        } else if (this == RPGSlotType.PET) {
            return 34;
        } else if (this == RPGSlotType.HELMET) {
            return 13;
        } else if (this == RPGSlotType.CHESTPLATE) {
            return 22;
        } else if (this == RPGSlotType.LEGGINGS) {
            return 31;
        } else if (this == RPGSlotType.BOOTS) {
            return 40;
        } else if (this == RPGSlotType.OFFHAND) {
            return 21;
        } else if (this == RPGSlotType.HOE) {
            return 50;
        } else if (this == RPGSlotType.PICKAXE) {
            return 51;
        } else if (this == RPGSlotType.AXE) {
            return 52;
        } else if (this == RPGSlotType.BOTTLE) {
            return 53;
        }

        return 30;
    }

    public String getDisplayName() {
        if (this == RPGSlotType.PARROT) {
            return "Parrot";
        } else if (this == RPGSlotType.EARRING) {
            return "Earring";
        } else if (this == RPGSlotType.NECKLACE) {
            return "Necklace";
        } else if (this == RPGSlotType.GLOVE) {
            return "Glove";
        } else if (this == RPGSlotType.RING) {
            return "Ring";
        } else if (this == RPGSlotType.CHARACTER_INFO) {
            return "Character Info";
        } else if (this == RPGSlotType.PET) {
            return "Pet";
        } else if (this == RPGSlotType.HELMET) {
            return "Helmet";
        } else if (this == RPGSlotType.CHESTPLATE) {
            return "Chestplate";
        } else if (this == RPGSlotType.LEGGINGS) {
            return "Leggings";
        } else if (this == RPGSlotType.BOOTS) {
            return "Boots";
        } else if (this == RPGSlotType.OFFHAND) {
            return "Offhand";
        } else if (this == RPGSlotType.HOE) {
            return "Hoe";
        } else if (this == RPGSlotType.PICKAXE) {
            return "Pickaxe";
        } else if (this == RPGSlotType.AXE) {
            return "Axe";
        } else if (this == RPGSlotType.BOTTLE) {
            return "Bottle";
        }

        return "Mainhand";
    }

    public int getCustomModelDataOffset() { //for passive sets
        if (this == RPGSlotType.PARROT) {
            return 40;
        } else if (this == RPGSlotType.EARRING) {
            return 30;
        } else if (this == RPGSlotType.NECKLACE) {
            return 20;
        } else if (this == RPGSlotType.GLOVE) {
            return 10;
        } else if (this == RPGSlotType.RING) {
            return 0;
        }

        return 0;
    }

    public int getReqLevelOffset() { //for passive sets
        if (this == RPGSlotType.PARROT) {
            return 9;
        } else if (this == RPGSlotType.NECKLACE) {
            return 5;
        } else if (this == RPGSlotType.GLOVE) {
            return 7;
        } else if (this == RPGSlotType.EARRING) {
            return 3;
        } else if (this == RPGSlotType.RING) {
            return 1;
        }

        return 9999;
    }
}
