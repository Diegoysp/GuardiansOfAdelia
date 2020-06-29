package io.github.lix3nn53.guardiansofadelia.guardian.skill.list;

import io.github.lix3nn53.guardiansofadelia.guardian.skill.Skill;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.condition.HealthCondition;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.*;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.immunity.ImmunityMechanic;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.immunity.ImmunityRemoveMechanic;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.immunity.InvincibleMechanic;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.projectile.ProjectileMechanic;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.projectile.SpreadType;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.statuseffect.DisarmMechanic;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.mechanic.statuseffect.SilenceMechanic;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.target.AreaTarget;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.target.SelfTarget;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.trigger.InitializeTrigger;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.trigger.LandTrigger;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.trigger.TookMagicalDamageTrigger;
import io.github.lix3nn53.guardiansofadelia.guardian.skill.component.trigger.TookPhysicalDamageTrigger;
import io.github.lix3nn53.guardiansofadelia.sounds.GoaSound;
import io.github.lix3nn53.guardiansofadelia.utilities.particle.ParticleArrangement;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaladinSkills {

    public static HashMap<Integer, Skill> getSet() {
        HashMap<Integer, Skill> skills = new HashMap<>();

        skills.put(0, getOne());
        skills.put(1, getTwo());
        skills.put(2, getThree());
        skills.put(3, getPassive());
        skills.put(4, getUltimate());

        return skills;
    }

    private static Skill getOne() {
        List<String> description = new ArrayList<>();
        description.add(ChatColor.GRAY + "Deal damage and push nearby enemies");

        List<Integer> reqLevels = new ArrayList<>();
        reqLevels.add(1);
        reqLevels.add(13);
        reqLevels.add(25);
        reqLevels.add(37);
        reqLevels.add(49);
        reqLevels.add(61);

        List<Integer> reqPoints = new ArrayList<>();
        reqPoints.add(1);
        reqPoints.add(1);
        reqPoints.add(2);
        reqPoints.add(2);
        reqPoints.add(3);
        reqPoints.add(3);

        List<Integer> manaCosts = new ArrayList<>();
        manaCosts.add(15);
        manaCosts.add(15);
        manaCosts.add(15);
        manaCosts.add(15);
        manaCosts.add(15);
        manaCosts.add(15);

        List<Integer> cooldowns = new ArrayList<>();
        cooldowns.add(10);
        cooldowns.add(10);
        cooldowns.add(10);
        cooldowns.add(10);
        cooldowns.add(10);
        cooldowns.add(10);

        Skill skill = new Skill("Hammerblow", 6, Material.IRON_HOE, 46, description, reqLevels, reqPoints, manaCosts, cooldowns);

        SelfTarget selfTarget = new SelfTarget();

        List<Double> areas = new ArrayList<>();
        areas.add(3D);
        areas.add(3.25D);
        areas.add(3.5D);
        areas.add(3.75D);
        areas.add(4D);
        areas.add(4.5D);
        AreaTarget areaTarget = new AreaTarget(false, true, false, 99, areas);

        List<Double> damages = new ArrayList<>();
        damages.add(20.0);
        damages.add(50.0);
        damages.add(150.0);
        damages.add(240.0);
        damages.add(360.0);
        damages.add(580.0);
        DamageMechanic damageMechanic = new DamageMechanic(damages, DamageMechanic.DamageType.MELEE);

        List<Double> speeds = new ArrayList<>();
        speeds.add(1.2D);
        speeds.add(1.3D);
        speeds.add(1.4D);
        speeds.add(1.5D);
        speeds.add(1.6D);
        speeds.add(1.8D);
        PushMechanic pushMechanic = new PushMechanic(PushMechanic.PushType.FIXED, speeds, true);

        skill.addTrigger(selfTarget);
        selfTarget.addChildren(areaTarget);
        areaTarget.addChildren(damageMechanic);
        areaTarget.addChildren(pushMechanic);

        ParticleMechanic particleMechanic = new ParticleMechanic(Particle.SWEEP_ATTACK, ParticleArrangement.CIRCLE, 3.4, 7, 0, 0, 0, 0, 1, 0, 0, null);

        SelfTarget selfTargetForSound = new SelfTarget();
        selfTargetForSound.addChildren(particleMechanic);
        areaTarget.addChildren(selfTargetForSound);
        selfTargetForSound.addChildren(new SoundMechanic(GoaSound.SKILL_WIND_PUSH));

        return skill;
    }

    private static Skill getTwo() {
        List<String> description = new ArrayList<>();
        description.add(ChatColor.GRAY + "Shoot a projectile and heal allies");
        description.add(ChatColor.GRAY + "around where it hits.");

        List<Integer> reqLevels = new ArrayList<>();
        reqLevels.add(8);
        reqLevels.add(20);
        reqLevels.add(32);
        reqLevels.add(44);
        reqLevels.add(56);
        reqLevels.add(68);

        List<Integer> reqPoints = new ArrayList<>();
        reqPoints.add(2);
        reqPoints.add(2);
        reqPoints.add(3);
        reqPoints.add(3);
        reqPoints.add(4);
        reqPoints.add(4);

        List<Integer> manaCosts = new ArrayList<>();
        manaCosts.add(20);
        manaCosts.add(22);
        manaCosts.add(24);
        manaCosts.add(26);
        manaCosts.add(28);
        manaCosts.add(30);

        List<Integer> cooldowns = new ArrayList<>();
        cooldowns.add(18);
        cooldowns.add(18);
        cooldowns.add(18);
        cooldowns.add(18);
        cooldowns.add(18);
        cooldowns.add(18);

        Skill skill = new Skill("Heal", 6, Material.IRON_HOE, 37, description, reqLevels, reqPoints, manaCosts, cooldowns);

        List<Integer> projectileAmounts = new ArrayList<>();
        projectileAmounts.add(1);
        projectileAmounts.add(1);
        projectileAmounts.add(1);
        projectileAmounts.add(1);
        projectileAmounts.add(1);
        projectileAmounts.add(1);
        ProjectileMechanic projectileMechanic = new ProjectileMechanic(SpreadType.CONE, 1.9, projectileAmounts, 30,
                0, 1, 0, 200, false, SmallFireball.class, Particle.HEART, ParticleArrangement.SPHERE, 0.5, 4, null, true);

        List<Integer> amounts = new ArrayList<>();
        amounts.add(200);
        amounts.add(500);
        amounts.add(1000);
        amounts.add(1600);
        amounts.add(2200);
        amounts.add(3600);
        HealMechanic healMechanic = new HealMechanic(amounts, new ArrayList<>());

        ParticleMechanic particleMechanic = new ParticleMechanic(Particle.HEART, ParticleArrangement.CIRCLE, 1.4, 8, 0, 0, 0, 0, 0.5, 0, 0, null);

        SelfTarget selfTarget = new SelfTarget();
        skill.addTrigger(selfTarget);
        selfTarget.addChildren(projectileMechanic);

        List<Double> areas = new ArrayList<>();
        areas.add(3D);
        areas.add(3.25D);
        areas.add(3.5D);
        areas.add(3.75D);
        areas.add(4D);
        areas.add(4.5D);
        AreaTarget areaTarget = new AreaTarget(true, false, true, 99, areas);

        projectileMechanic.addChildren(areaTarget);
        areaTarget.addChildren(particleMechanic);
        areaTarget.addChildren(healMechanic);
        projectileMechanic.addChildren(new SoundMechanic(GoaSound.SKILL_HEAL));

        return skill;
    }

    private static Skill getThree() {
        List<String> description = new ArrayList<>();
        description.add(ChatColor.GRAY + "Transform nearby enemies into pigs!");

        List<Integer> reqLevels = new ArrayList<>();
        reqLevels.add(12);
        reqLevels.add(24);
        reqLevels.add(36);
        reqLevels.add(48);
        reqLevels.add(60);
        reqLevels.add(72);

        List<Integer> reqPoints = new ArrayList<>();
        reqPoints.add(2);
        reqPoints.add(2);
        reqPoints.add(3);
        reqPoints.add(3);
        reqPoints.add(4);
        reqPoints.add(4);

        List<Integer> manaCosts = new ArrayList<>();
        manaCosts.add(20);
        manaCosts.add(22);
        manaCosts.add(24);
        manaCosts.add(26);
        manaCosts.add(28);
        manaCosts.add(30);

        List<Integer> cooldowns = new ArrayList<>();
        cooldowns.add(24);
        cooldowns.add(24);
        cooldowns.add(24);
        cooldowns.add(24);
        cooldowns.add(24);
        cooldowns.add(24);

        Skill skill = new Skill("Polymorph", 6, Material.IRON_HOE, 55, description, reqLevels, reqPoints, manaCosts, cooldowns);

        SelfTarget selfTarget = new SelfTarget();

        List<Double> areas = new ArrayList<>();
        areas.add(4D);
        areas.add(5D);
        areas.add(6D);
        areas.add(7D);
        areas.add(8D);
        areas.add(10D);
        AreaTarget areaTarget = new AreaTarget(false, true, false, 99, areas);

        List<Integer> ticks = new ArrayList<>();
        ticks.add(60);
        ticks.add(65);
        ticks.add(70);
        ticks.add(75);
        ticks.add(80);
        ticks.add(100);
        DisguiseMechanic disguiseMechanic = new DisguiseMechanic(DisguiseType.PIG, true, ticks);

        List<Integer> amplifiers = new ArrayList<>();
        amplifiers.add(2);
        amplifiers.add(3);
        amplifiers.add(4);
        amplifiers.add(5);
        amplifiers.add(6);
        amplifiers.add(7);
        PotionEffectMechanic slow = new PotionEffectMechanic(PotionEffectType.SLOW, ticks, amplifiers);

        SilenceMechanic silenceMechanic = new SilenceMechanic(ticks);

        DisarmMechanic disarmMechanic = new DisarmMechanic(ticks);

        skill.addTrigger(selfTarget);
        selfTarget.addChildren(areaTarget);
        areaTarget.addChildren(disguiseMechanic);
        areaTarget.addChildren(slow);
        areaTarget.addChildren(disarmMechanic);
        areaTarget.addChildren(silenceMechanic);

        ParticleMechanic particleMechanic = new ParticleMechanic(Particle.SPELL_WITCH, ParticleArrangement.CIRCLE, 6.7, 27, 0, 0, 0, 0, 0.5, 0, 0, null);
        SelfTarget selfTargetForSound = new SelfTarget();
        selfTargetForSound.addChildren(particleMechanic);
        areaTarget.addChildren(selfTargetForSound);
        selfTargetForSound.addChildren(new SoundMechanic(GoaSound.SKILL_PIG));

        return skill;
    }

    private static Skill getPassive() {
        List<String> description = new ArrayList<>();
        description.add(ChatColor.GRAY + "If you take a damage that leaves you with less than 15% health,");
        description.add(ChatColor.GRAY + "fly into the sky like an angel and heal yourself.");

        List<Integer> reqLevels = new ArrayList<>();
        reqLevels.add(20);
        reqLevels.add(35);
        reqLevels.add(50);
        reqLevels.add(60);
        reqLevels.add(70);
        reqLevels.add(80);

        List<Integer> reqPoints = new ArrayList<>();
        reqPoints.add(2);
        reqPoints.add(3);
        reqPoints.add(3);
        reqPoints.add(4);
        reqPoints.add(4);
        reqPoints.add(5);

        List<Integer> manaCosts = new ArrayList<>();
        manaCosts.add(0);
        manaCosts.add(0);
        manaCosts.add(0);
        manaCosts.add(0);
        manaCosts.add(0);
        manaCosts.add(0);

        List<Integer> cooldowns = new ArrayList<>();
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);

        Skill skill = new Skill("Resurrection", 6, Material.IRON_HOE, 35, description, reqLevels, reqPoints, manaCosts, cooldowns);

        InitializeTrigger initializeTrigger = new InitializeTrigger();

        TookPhysicalDamageTrigger tookPhysicalDamageTrigger = new TookPhysicalDamageTrigger(cooldowns);
        TookMagicalDamageTrigger tookMagicalDamageTrigger = new TookMagicalDamageTrigger(cooldowns);
        SelfTarget selfTarget = new SelfTarget();

        HealthCondition healthCondition = new HealthCondition(0.0, 0.15);

        skill.addTrigger(initializeTrigger);

        initializeTrigger.addChildren(tookPhysicalDamageTrigger);
        tookPhysicalDamageTrigger.addChildren(selfTarget);
        selfTarget.addChildren(healthCondition);
        List<Double> percents = new ArrayList<>();
        percents.add(0.4);
        percents.add(0.45);
        percents.add(0.5);
        percents.add(0.55);
        percents.add(0.60);
        percents.add(0.8);
        HealMechanic healMechanic = new HealMechanic(new ArrayList<>(), percents);
        healthCondition.addChildren(healMechanic);

        initializeTrigger.addChildren(tookMagicalDamageTrigger);
        tookMagicalDamageTrigger.addChildren(selfTarget);

        List<Integer> ticks = new ArrayList<>();
        ticks.add(120);
        ticks.add(120);
        ticks.add(120);
        ticks.add(120);
        ticks.add(120);
        ticks.add(120);
        List<Integer> amplifiers = new ArrayList<>();
        amplifiers.add(6);
        amplifiers.add(6);
        amplifiers.add(6);
        amplifiers.add(6);
        amplifiers.add(6);
        amplifiers.add(6);
        PotionEffectMechanic levitation = new PotionEffectMechanic(PotionEffectType.LEVITATION, ticks, amplifiers);
        PotionEffectMechanic invis = new PotionEffectMechanic(PotionEffectType.INVISIBILITY, ticks, amplifiers);
        PotionEffectMechanic glowing = new PotionEffectMechanic(PotionEffectType.GLOWING, ticks, amplifiers);
        List<Integer> ticksImmunity = new ArrayList<>();
        ticksImmunity.add(240);
        ticksImmunity.add(240);
        ticksImmunity.add(240);
        ticksImmunity.add(240);
        ticksImmunity.add(240);
        ticksImmunity.add(240);
        ImmunityMechanic immunityMechanic = new ImmunityMechanic(EntityDamageEvent.DamageCause.FALL, ticksImmunity);

        healthCondition.addChildren(levitation);
        healthCondition.addChildren(invis);
        healthCondition.addChildren(glowing);
        healthCondition.addChildren(immunityMechanic);
        healthCondition.addChildren(new SoundMechanic(GoaSound.SKILL_BUFF));

        return skill;
    }

    private static Skill getUltimate() {
        List<String> description = new ArrayList<>();
        description.add(ChatColor.GRAY + "Jump into the sky, give invincible effect");
        description.add(ChatColor.GRAY + "to yourself and nearby allies when you land.");

        List<Integer> reqLevels = new ArrayList<>();
        reqLevels.add(40);
        reqLevels.add(50);
        reqLevels.add(60);
        reqLevels.add(70);
        reqLevels.add(80);
        reqLevels.add(90);

        List<Integer> reqPoints = new ArrayList<>();
        reqPoints.add(5);
        reqPoints.add(6);
        reqPoints.add(7);
        reqPoints.add(8);
        reqPoints.add(9);
        reqPoints.add(10);

        List<Integer> manaCosts = new ArrayList<>();
        manaCosts.add(40);
        manaCosts.add(42);
        manaCosts.add(44);
        manaCosts.add(46);
        manaCosts.add(48);
        manaCosts.add(50);

        List<Integer> cooldowns = new ArrayList<>();
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);
        cooldowns.add(60);

        Skill skill = new Skill("Cosmic Radiance", 6, Material.IRON_HOE, 18, description, reqLevels, reqPoints, manaCosts, cooldowns);

        SelfTarget selfTarget = new SelfTarget();

        List<Double> forwards = new ArrayList<>();
        forwards.add(1.4);
        forwards.add(1.5);
        forwards.add(1.6);
        forwards.add(1.7);
        forwards.add(1.8);
        forwards.add(2D);
        List<Double> upwards = new ArrayList<>();
        upwards.add(1.7D);
        upwards.add(1.75D);
        upwards.add(1.8);
        upwards.add(1.85);
        upwards.add(1.9);
        upwards.add(2D);
        List<Double> right = new ArrayList<>();
        right.add(0D);
        right.add(0D);
        right.add(0D);
        right.add(0D);
        right.add(0D);
        right.add(0D);
        LaunchMechanic launchMechanic = new LaunchMechanic(LaunchMechanic.Relative.TARGET, forwards, upwards, right);

        LandTrigger landTrigger = new LandTrigger();

        ImmunityMechanic immunityMechanic = new ImmunityMechanic(EntityDamageEvent.DamageCause.FALL, new ArrayList<>()); //0 for infinite
        ImmunityRemoveMechanic immunityRemoveMechanic = new ImmunityRemoveMechanic(EntityDamageEvent.DamageCause.FALL, 5);

        List<Integer> repeatAmounts = new ArrayList<>();
        repeatAmounts.add(10);
        repeatAmounts.add(12);
        repeatAmounts.add(14);
        repeatAmounts.add(16);
        repeatAmounts.add(18);
        repeatAmounts.add(20);
        ParticleAnimationMechanic particleAnimationMechanic = new ParticleAnimationMechanic(Particle.REDSTONE, ParticleArrangement.CIRCLE, 1, 3, 0,
                0, 0, 0, 0, 0, 1, 5L, repeatAmounts, new Particle.DustOptions(Color.YELLOW, 2));

        List<Double> radiuses = new ArrayList<>();
        radiuses.add(8D);
        radiuses.add(9D);
        radiuses.add(10D);
        radiuses.add(11D);
        radiuses.add(12D);
        radiuses.add(14D);
        AreaTarget areaTarget = new AreaTarget(true, false, true, 99, radiuses);

        ParticleMechanic particleMechanic = new ParticleMechanic(Particle.REDSTONE, ParticleArrangement.CIRCLE, 8, 3, -0.6, 0.4, 0.1, 0, 0, 0, 1, new Particle.DustOptions(Color.YELLOW, 2));

        skill.addTrigger(selfTarget);

        selfTarget.addChildren(launchMechanic);
        selfTarget.addChildren(immunityMechanic);
        selfTarget.addChildren(particleAnimationMechanic);
        selfTarget.addChildren(landTrigger);

        landTrigger.addChildren(particleMechanic);
        landTrigger.addChildren(new SoundMechanic(GoaSound.SKILL_BUFF));
        landTrigger.addChildren(immunityRemoveMechanic);
        landTrigger.addChildren(areaTarget);
        List<Integer> ticks = new ArrayList<>();
        ticks.add(120);
        ticks.add(130);
        ticks.add(140);
        ticks.add(150);
        ticks.add(160);
        ticks.add(200);
        areaTarget.addChildren(new InvincibleMechanic(ticks));

        List<Integer> repeatAmounts2 = new ArrayList<>();
        repeatAmounts2.add(24);
        repeatAmounts2.add(26);
        repeatAmounts2.add(28);
        repeatAmounts2.add(30);
        repeatAmounts2.add(32);
        repeatAmounts2.add(40);
        ParticleAnimationMechanic particleAnimationMechanic2 = new ParticleAnimationMechanic(Particle.REDSTONE, ParticleArrangement.CIRCLE, 1, 3,
                -0.1, 1, 0, 0, 0.5, 0, 1, 5L, repeatAmounts2, new Particle.DustOptions(Color.YELLOW, 2));

        areaTarget.addChildren(particleAnimationMechanic2);

        return skill;
    }
}
