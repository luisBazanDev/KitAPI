package pe.bazan.luis.plugins.kitsapi.api.domain;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import pe.bazan.luis.plugins.kitsapi.domain.Kit;
import pe.bazan.luis.plugins.kitsapi.tasks.PlayerTracker;

import java.util.HashMap;

public class PluginKitsManager {
    final private Plugin plugin;
    final private HashMap<Player, PlayerTracker> playerTrackers;

    public PluginKitsManager(Plugin plugin) {
        this.plugin = plugin;
        this.playerTrackers = new HashMap<>();
    }

    public void setPermanentKit(Player player, Kit kit) {
        PlayerTracker playerTracker = playerTrackers.get(player);

        if (playerTracker != null) {
            playerTracker.setKit(kit);
            return;
        }

        playerTracker = new PlayerTracker(player, kit);
        playerTrackers.put(player, playerTracker);
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, playerTracker, 0, 100L);
        playerTracker.setTaskId(bukkitTask.getTaskId());
    }

    public void cancelTrack(Player player) {
        PlayerTracker playerTracker = playerTrackers.get(player);
        if (playerTracker == null) return;
        Bukkit.getScheduler().cancelTask(playerTracker.getTaskId());
        playerTrackers.remove(player);
    }
}
