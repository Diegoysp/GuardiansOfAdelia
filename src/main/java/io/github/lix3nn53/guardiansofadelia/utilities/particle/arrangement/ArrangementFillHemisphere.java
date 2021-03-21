package io.github.lix3nn53.guardiansofadelia.utilities.particle.arrangement;

import io.github.lix3nn53.guardiansofadelia.utilities.particle.ParticleShapes;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;

public class ArrangementFillHemisphere extends ArrangementWithRadius {

    private final int amount;

    public ArrangementFillHemisphere(Particle particle, double radius, int amount, Particle.DustOptions dustOptions) {
        super(particle, dustOptions, radius);
        this.amount = amount;
    }

    public ArrangementFillHemisphere(ConfigurationSection configurationSection) {
        super(configurationSection);

        if (!configurationSection.contains("radius")) {
            configLoadError("radius");
        }

        if (!configurationSection.contains("amount")) {
            configLoadError("amount");
        }

        this.radius = configurationSection.getDouble("radius");
        this.amount = configurationSection.getInt("amount");
    }

    @Override
    public void play(Location location, @Nullable Vector offset) {
        Location location1 = location.clone();
        if (offset != null) {
            location1.add(offset);
        }
        ParticleShapes.fillHemisphere(location1, particle, radius, amount, dustOptions);
    }

    @Override
    public void play(Location location, @Nullable Vector offset, float yaw, float pitch) {
        Location location1 = location.clone();
        if (offset != null) {
            location1.add(offset);
        }
        ParticleShapes.fillHemisphere(location1, particle, radius, amount, dustOptions);
    }
}