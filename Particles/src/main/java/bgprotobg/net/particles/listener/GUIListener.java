package bgprotobg.net.particles.listener;

import bgprotobg.net.particles.manager.ParticleManager;
import bgprotobg.net.particles.model.ParticleEffect;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIListener implements Listener {

    private final JavaPlugin plugin;

    public GUIListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        String title = inventory.getViewers().get(0).getOpenInventory().getTitle();
        if (title.equals("Select your trail")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            int slot = event.getSlot();
            if (slot == 40) {
                ParticleManager.getInstance().clearPlayerParticle(player);
                player.sendMessage(ChatColor.GREEN + "Trail removed.");
            } else if (slot >= 10 && slot < 35 && (slot % 9 != 0 && (slot + 1) % 9 != 0)) {
                int index = slot - 10 - (slot / 9 - 1) * 2;
                ParticleEffect effect = ParticleManager.getInstance().getParticles().values().toArray(new ParticleEffect[0])[index];
                if (effect != null && player.hasPermission(effect.getPermission())) {
                    ParticleManager.getInstance().setPlayerParticle(player, effect);
                    player.sendMessage(ChatColor.GREEN + "Selected trail: "  + effect.getParticle().name());
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have permission for this trail.");
                }
                player.closeInventory();
            }
        }
    }
}
