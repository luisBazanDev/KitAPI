package pe.bazan.luis.plugins.kitsapi.api;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.api.domain.PluginKitsManager;
import pe.bazan.luis.plugins.kitsapi.domain.Kit;

import javax.annotation.Nullable;
import java.util.HashMap;

public class KitsHelper {
    private static final HashMap<Plugin, PluginKitsManager> pluginsKitManagers = new HashMap<>();

    public static void registerPluginKit(String plugin, Kit kit) {
        kit.setName(plugin + "-" + kit.getName());
        KitsAPI.getManager().saveKit(kit);
    }

    public static void registerPluginKit(JavaPlugin plugin, Kit kit) {
        registerPluginKit(plugin.getName(), kit);
    }

    public static @Nullable Kit getPluginKit(JavaPlugin plugin, String name) {
        return KitsAPI.getKit(plugin.getName() + "-" + name);
    }

    public static @Nullable Kit getPluginKit(String plugin, String name) {
        return KitsAPI.getKit(plugin + "-" + name);
    }

    public static Kit resolvePluginKit(String plugin, String name) {
        final String kitName = plugin + "-" + name;
        Kit kit = KitsAPI.getKit(kitName);

        if (kit != null) return kit;

        kit = new KitsBuilder(kitName).build();
        kit.save();
        return kit;
    }

    public static Kit resolvePluginKit(JavaPlugin plugin, String name) {
        return resolvePluginKit(plugin.getName(), name);
    }

    public static PluginKitsManager getKitManager(Plugin plugin) {
        PluginKitsManager pluginKitsManager = pluginsKitManagers.get(plugin);
        if (pluginKitsManager != null) return pluginKitsManager;
        pluginsKitManagers.put(plugin, new PluginKitsManager(plugin));
        return pluginsKitManagers.get(plugin);
    }
}
