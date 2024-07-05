package bgprotobg.net.particles;

import bgprotobg.net.particles.command.TrailsCommand;
import bgprotobg.net.particles.listener.GUIListener;
import bgprotobg.net.particles.listener.PlayerListener;
import bgprotobg.net.particles.manager.ParticleManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Particles extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.config = getConfig();
        ParticleManager particleManager = ParticleManager.getInstance();
        particleManager.loadParticles(config);
        File dataFile = new File(getDataFolder(), "data.yml");
        particleManager.loadDataFile(dataFile);
        particleManager.loadPlayerParticles();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        this.getCommand("trails").setExecutor(new TrailsCommand(this));
        this.getServer().getPluginManager().registerEvents(new GUIListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }
}
