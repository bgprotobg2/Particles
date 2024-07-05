package bgprotobg.net.particles.gui;

import bgprotobg.net.particles.manager.ParticleManager;
import bgprotobg.net.particles.model.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ParticleGUI {

    public static void openParticleGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 45, "Select your trail");
        ParticleManager particleManager = ParticleManager.getInstance();
        int index = 10;
        for (ParticleEffect effect : particleManager.getParticles().values()) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.YELLOW + effect.getParticle().name());
                item.setItemMeta(meta);
            }
            gui.setItem(index, item);
            index++;
            if ((index + 1) % 9 == 0) {
                index += 2;
            }
        }
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrier.getItemMeta();
        if (barrierMeta != null) {
            barrierMeta.setDisplayName(ChatColor.RED + "Remove trail");
            barrier.setItemMeta(barrierMeta);
        }
        gui.setItem(40, barrier);
        player.openInventory(gui);
    }
}
