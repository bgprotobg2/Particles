package bgprotobg.net.particles.model;

import org.bukkit.Particle;

public class ParticleEffect {
    private final Particle particle;
    private final String permission;
    private final float size;

    public ParticleEffect(Particle particle, String permission, float size) {
        this.particle = particle;
        this.permission = permission;
        this.size = size;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getPermission() {
        return permission;
    }

    public float getSize() {
        return size;
    }
}
