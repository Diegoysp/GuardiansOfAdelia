package io.github.lix3nn53.guardiansofadelia.transportation.portals;

import io.github.lix3nn53.guardiansofadelia.guardian.GuardianData;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianDataManager;
import io.github.lix3nn53.guardiansofadelia.guardian.character.RPGCharacter;
import io.github.lix3nn53.guardiansofadelia.quests.Quest;
import io.github.lix3nn53.guardiansofadelia.text.ChatPalette;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class InstantTeleportPortal {

    private final Location destination;
    private final int requiredQuestNoAccepted;
    private final int requiredQuestNoTurnedIn;

    public InstantTeleportPortal(Location destination, int requiredQuestNoAccepted, int requiredQuestNoTurnedIn) {
        this.destination = destination;
        this.requiredQuestNoAccepted = requiredQuestNoAccepted;
        this.requiredQuestNoTurnedIn = requiredQuestNoTurnedIn;
    }

    public Location getDestination() {
        return destination;
    }

    public boolean canTeleport(Player player) {
        if (requiredQuestNoAccepted == 0 && requiredQuestNoTurnedIn == 0) return true;

        if (GuardianDataManager.hasGuardianData(player)) {
            GuardianData guardianData = GuardianDataManager.getGuardianData(player);
            if (guardianData.hasActiveCharacter()) {

                RPGCharacter activeCharacter = guardianData.getActiveCharacter();

                if (requiredQuestNoAccepted > 0) {
                    List<Quest> questList = activeCharacter.getQuestList();

                    boolean questIsInList = questList.stream().anyMatch(questInList -> questInList.getQuestID() == requiredQuestNoAccepted);

                    if (!questIsInList) {
                        player.sendMessage(ChatPalette.RED + "You need to accept quest#" + requiredQuestNoAccepted + " to enter this portal.");
                        return false;
                    }
                }

                if (requiredQuestNoTurnedIn > 0) {
                    List<Integer> turnedInQuests = activeCharacter.getTurnedInQuests();

                    if (!turnedInQuests.contains(requiredQuestNoTurnedIn)) {
                        player.sendMessage(ChatPalette.RED + "You need to turn in quest#" + requiredQuestNoTurnedIn + " to enter this portal.");
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
