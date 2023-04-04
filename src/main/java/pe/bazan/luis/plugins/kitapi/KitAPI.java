package pe.bazan.luis.plugins.kitapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class KitAPI extends JavaPlugin {
    private static KitAPI instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static KitAPI getInstance() {
        return instance;
    }
}
