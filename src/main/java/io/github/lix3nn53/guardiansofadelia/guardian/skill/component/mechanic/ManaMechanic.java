package io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic;

import io.github.lix3nn53.guardiansofadelia.guardian.GuardianData;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianDataManager;
import io.github.lix3nn53.guardiansofadelia.guardian.character.RPGCharacter;
import io.github.lix3nn53.guardiansofadelia.guardian.character.RPGCharacterStats;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.MechanicComponent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManaMechanic extends MechanicComponent {

    private final double manaAmount;
    private final double manaPercent;

    public ManaMechanic(double manaAmount, double manaPercent) {
        this.manaAmount = manaAmount;
        this.manaPercent = manaPercent;
    }

    @Override
    public boolean execute(LivingEntity caster, int skillLevel, List<LivingEntity> targets, String castKey) {
        if (targets.isEmpty()) return false;

        for (LivingEntity ent : targets) {
            if (ent instanceof Player) {
                Player player = (Player) ent;
                UUID uuid = player.getUniqueId();
                if (GuardianDataManager.hasGuardianData(uuid)) {
                    GuardianData guardianData = GuardianDataManager.getGuardianData(uuid);
                    if (guardianData.hasActiveCharacter()) {
                        RPGCharacter activeCharacter = guardianData.getActiveCharacter();
                        RPGCharacterStats rpgCharacterStats = activeCharacter.getRpgCharacterStats();
                        int currentMana = rpgCharacterStats.getCurrentMana();

                        double maxMana = rpgCharacterStats.getTotalMaxMana();

                        double nextMana = currentMana + manaAmount;

                        if (manaPercent > 0) {
                            nextMana = nextMana + (maxMana * manaPercent);
                        }

                        if (nextMana > maxMana) {
                            nextMana = maxMana;
                        }

                        rpgCharacterStats.setCurrentMana((int) nextMana);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public List<String> getSkillLoreAdditions(int skillLevel) {
        List<String> lore = new ArrayList<>();
        return lore;
    }
}