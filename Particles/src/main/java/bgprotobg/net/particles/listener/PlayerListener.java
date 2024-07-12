package bgprotobg.net.particles.listener;

import bgprotobg.net.particles.manager.ParticleManager;
import bgprotobg.net.particles.model.ParticleEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener {
    private final Plugin plugin;

    public PlayerListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        ParticleEffect effect = ParticleManager.getInstance().getPlayerParticle(player);

        if (effect != null && player.hasPermission(effect.getPermission())) {
            player.getWorld().spawnParticle(effect.getParticle(), player.getLocation(), 10, 0, 0, 0, effect.getSize());
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ParticleEffect effect = ParticleManager.getInstance().getPlayerParticle(player);

        if (player.isFlying() && effect != null && player.hasPermission(effect.getPermission())) {
            player.getWorld().spawnParticle(effect.getParticle(), player.getLocation(), 10, 0, 0, 0, effect.getSize());
        }
    }
}
