package pe.bazan.luis.plugins.kitsapi;

import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.kitsapi.api.KitsHelper;
import pe.bazan.luis.plugins.kitsapi.commands.MainCommand;
import pe.bazan.luis.plugins.kitsapi.domain.Kit;

import javax.annotation.Nullable;
import java.util.List;

public final class KitsAPI extends JavaPlugin {
    private static KitsAPI instance;
    private KitsHelper kitsHelper;
    private KitsManager kitsManager;
    private Metrics metrics;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.kitsManager = new KitsManager();
        this.kitsHelper = new KitsHelper();
        this.metrics = new Metrics(this, 18155);
        registerCommands();
    }

    public void registerCommands() {
        getCommand("kitsapi").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public KitsManager getKitsManager() {
        return kitsManager;
    }

    public KitsHelper getKitsHelper() {
        return kitsHelper;
    }

    public static KitsAPI getInstance() {
        return instance;
    }

    public static KitsHelper getHelper() {
        return instance.getKitsHelper();
    }

    public static @Nullable Kit getKit(String name) {
        return getManager().getKit(name);
    }

    public static List<Kit> getKits() {
        return getManager().getKits().values().stream().toList();
    }

    public static KitsManager getManager() {
        return instance.getKitsManager();
    }
}
