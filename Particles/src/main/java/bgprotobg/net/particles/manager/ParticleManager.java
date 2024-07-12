package bgprotobg.net.particles.manager;

import bgprotobg.net.particles.model.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParticleManager {
    private static ParticleManager instance;
    private final Map<String, ParticleEffect> particles = new HashMap<>();
    private final Map<UUID, ParticleEffect> playerParticles = new HashMap<>();
    private File dataFile;
    private FileConfiguration dataConfig;

    private ParticleManager() {}

    public static ParticleManager getInstance() {
        if (instance == null) {
            instance = new ParticleManager();
        }
        return instance;
    }

    public void loadParticles(FileConfiguration config) {
        List<Map<?, ?>> particleList = config.getMapList("particles");
        for (Map<?, ?> particleConfig : particleList) {
            try {
                Particle particle = Particle.valueOf((String) particleConfig.get("type"));
                String permission = (String) particleConfig.get("permission");
                float size = particleConfig.containsKey("size") ? ((Number) particleConfig.get("size")).floatValue() : 1.0f;
                particles.put(permission, new ParticleEffect(particle, permission, size));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid trail type: " + particleConfig.get("type"));
            }
        }
    }

    public ParticleEffect getParticleEffect(String permission) {
        return particles.get(permission);
    }

    public Map<String, ParticleEffect> getParticles() {
        return particles;
    }

    public void setPlayerParticle(Player player, ParticleEffect effect) {
        playerParticles.put(player.getUniqueId(), effect);
        savePlayerParticle(player);
    }

    public ParticleEffect getPlayerParticle(Player player) {
        return playerParticles.get(player.getUniqueId());
    }

    public void clearPlayerParticle(Player player) {
        playerParticles.remove(player.getUniqueId());
        savePlayerParticle(player);
    }

    public void loadDataFile(File dataFile) {
        this.dataFile = dataFile;
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void loadPlayerParticles() {
        if (dataConfig == null) return;
        for (String key : dataConfig.getKeys(false)) {
            UUID playerUUID = UUID.fromString(key);
            String particleName = dataConfig.getString(key);
            ParticleEffect effect = getParticles().values().stream()
                    .filter(pe -> pe.getParticle().name().equals(particleName))
                    .findFirst()
                    .orElse(null);
            if (effect != null) {
                playerParticles.put(playerUUID, effect);
            }
        }
    }

    public void savePlayerParticle(Player player) {
        if (dataConfig == null) return;
        if (playerParticles.containsKey(player.getUniqueId())) {
            dataConfig.set(player.getUniqueId().toString(), playerParticles.get(player.getUniqueId()).getParticle().name());
        } else {
            dataConfig.set(player.getUniqueId().toString(), null);
        }
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
