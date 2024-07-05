package bgprotobg.net.particles.command;

import bgprotobg.net.particles.gui.ParticleGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TrailsCommand implements CommandExecutor {
    private final Plugin plugin;

    public TrailsCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ParticleGUI.openParticleGUI(player);
            return true;
        }
        return false;
    }
}
