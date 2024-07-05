package bgprotobg.net.particles.model;

import org.bukkit.Particle;

public class ParticleEffect {
    private final Particle particle;
    private final String permission;

    public ParticleEffect(Particle particle, String permission) {
        this.particle = particle;
        this.permission = permission;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getPermission() {
        return permission;
    }
}
