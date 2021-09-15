package io.github.lix3nn53.guardiansofadelia.bossbar;

import io.github.lix3nn53.guardiansofadelia.utilities.ChatPalette;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class HealthBar {

    private final BossBar bar;

    public HealthBar(String title, double progress) {
        BarColor color = BarColor.GREEN;

        if (progress < 0.6 && progress > 0.25) {
            color = BarColor.YELLOW;
        } else if (progress <= 0.25) {
            color = BarColor.RED;
        }

        this.bar = Bukkit.createBossBar(title, color, BarStyle.SEGMENTED_10);

        setProgress(progress);
        setVisible();
    }

    public HealthBar(LivingEntity livingTarget, int damage, ChatPalette damageColor, String damageIcon) {
        double maxHealth = livingTarget.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double currentHealth = livingTarget.getHealth() - damage;
        double progress = 0;
        if (currentHealth <= 0) {
            currentHealth = 0;
        } else {
            progress = currentHealth / maxHealth;
        }

        String targetName = "Target";
        if (livingTarget.isCustomNameVisible()) {
            targetName = livingTarget.getCustomName();
        }

        String title = (damageColor.toString() + damage + damageColor + damageIcon + " " + targetName + " " + ChatPalette.GREEN_DARK + (int) (currentHealth + 0.5) +
                ChatPalette.GRAY + "/" + ChatPalette.GREEN_DARK + (int) (maxHealth + 0.5) + ChatPalette.RED + "❤");

        BarColor color = BarColor.GREEN;

        if (progress < 0.6 && progress > 0.25) {
            color = BarColor.YELLOW;
        } else if (progress <= 0.25) {
            color = BarColor.RED;
        }

        this.bar = Bukkit.createBossBar(title, color, BarStyle.SEGMENTED_10);

        setProgress(progress);
        setVisible();
    }

    public void addPlayer(Player player) {
        this.bar.addPlayer(player);
    }

    public void setVisible() {
        this.bar.setVisible(true);
    }

    public void setProgress(double progress) {
        this.bar.setProgress(progress);
    }

    public void destroy() {
        this.bar.removeAll();
    }
}