package io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.buff;

import io.github.lix3nn53.guardiansofadelia.GuardiansOfAdelia;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.MechanicComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AttributeBuffMechanic extends MechanicComponent {

    private final Attribute attributeType;
    private final List<Float> amounts;
    private final List<Integer> ticks;
    private final AttributeModifier.Operation operation;

    public AttributeBuffMechanic(Attribute attributeType, List<Float> amounts, List<Integer> ticks, AttributeModifier.Operation operation) {
        super(false);

        this.attributeType = attributeType;
        this.amounts = amounts;
        this.ticks = ticks;
        this.operation = operation;
    }

    public AttributeBuffMechanic(ConfigurationSection configurationSection) {
        super(!configurationSection.contains("addLore") || configurationSection.getBoolean("addLore"));

        if (!configurationSection.contains("attributeType")) {
            configLoadError("attributeType");
        }

        if (!configurationSection.contains("multipliers")) {
            configLoadError("multipliers");
        }

        if (!configurationSection.contains("ticks")) {
            configLoadError("ticks");
        }

        this.attributeType = Attribute.valueOf(configurationSection.getString("attributeType"));
        this.amounts = configurationSection.getFloatList("amounts");
        this.ticks = configurationSection.contains("ticks") ? configurationSection.getIntegerList("ticks") : new ArrayList<>();
        this.operation = AttributeModifier.Operation.valueOf(configurationSection.getString("operation"));
    }

    @Override
    public boolean execute(LivingEntity caster, int skillLevel, List<LivingEntity> targets, int castCounter, int skillIndex) {
        if (targets.isEmpty()) return false;

        List<LivingEntity> buffedEntities = new ArrayList<>();

        float amount = amounts.get(skillLevel - 1);
        //add +2 ticks to duration because of repeating buffs icons disappear otherwise. Amplifier 0 anyways
        int duration = ticks.isEmpty() ? Integer.MAX_VALUE : ticks.get(skillLevel - 1) + 2;

        for (LivingEntity ent : targets) {
            AttributeInstance attribute = ent.getAttribute(attributeType);
            if (attribute == null) {
                GuardiansOfAdelia.getInstance().getLogger().severe("AttributeBuffMechanic ATTR NULL");
                continue;
            }

            AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), "goaskill", amount, operation);
            attribute.addModifier(attributeModifier);

            buffedEntities.add(ent);
        }

        if (buffedEntities.isEmpty()) return false;

        if (this.ticks.isEmpty()) return true;
        new BukkitRunnable() { //remove buffs from buffed players after timeout

            @Override
            public void run() {
                for (LivingEntity ent : buffedEntities) {
                    AttributeInstance attribute = ent.getAttribute(attributeType);
                    if (attribute == null) {
                        GuardiansOfAdelia.getInstance().getLogger().severe("AttributeBuffMechanic ATTR NULL");
                        continue;
                    }

                    Collection<AttributeModifier> modifiers = attribute.getModifiers();
                    for (AttributeModifier modifier : modifiers) {
                        String name = modifier.getName();
                        if (name.equals("goaskill")) {
                            attribute.removeModifier(modifier);
                            break;
                        }
                    }
                }
            }
        }.runTaskLater(GuardiansOfAdelia.getInstance(), ticks.get(skillLevel - 1));

        return true;
    }

    @Override
    public List<String> getSkillLoreAdditions(String lang, List<String> additions, int skillLevel) {
        if (!this.addLore) return getSkillLoreAdditionsOfChildren(lang, additions, skillLevel);

        if (skillLevel == 0) {
            additions.add(attributeType.toString() + " bonus: " + amounts.get(skillLevel));
            additions.add(attributeType + " duration: " + (ticks.get(skillLevel) / 20));
        } else if (skillLevel == amounts.size()) {
            additions.add(attributeType.toString() + " bonus: " + amounts.get(skillLevel - 1));
            additions.add(attributeType + " duration: " + (ticks.get(skillLevel - 1) / 20));
        } else {
            additions.add(attributeType.toString() + " bonus: " + amounts.get(skillLevel - 1) + "x -> " + amounts.get(skillLevel) + "x");
            additions.add(attributeType + " duration: " + (ticks.get(skillLevel - 1) / 20) + " seconds -> " + (ticks.get(skillLevel) / 20));
        }

        return getSkillLoreAdditionsOfChildren(lang, additions, skillLevel);
    }
}
