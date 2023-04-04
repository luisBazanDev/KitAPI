package pe.bazan.luis.plugins.kitsapi;

import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.kitsapi.commands.MainCommand;

public final class KitsAPI extends JavaPlugin {
    private static KitsAPI instance;
    private KitsManager kitsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.kitsManager = new KitsManager();
        registerCommands();
    }

    public void registerCommands() {
        getCommand("kitsapi").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static KitsAPI getInstance() {
        return instance;
    }

    public KitsManager getKitsManager() {
        return kitsManager;
    }
}
