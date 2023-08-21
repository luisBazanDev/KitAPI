package pe.bazan.luis.plugins.kitsapi.api;

import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.domain.Kit;

import javax.annotation.Nullable;

public class KitsHelper {

    public void registerPluginKit(String plugin, Kit kit) {
        kit.setName(plugin + "-" + kit.getName());
        KitsAPI.getManager().saveKit(kit);
    }

    public void registerPluginKit(JavaPlugin plugin, Kit kit) {
        registerPluginKit(plugin.getName(), kit);
    }

    public @Nullable Kit getPluginKit(JavaPlugin plugin, String name) {
        return KitsAPI.getKit(plugin.getName() + "-" + name);
    }

    public @Nullable Kit getPluginKit(String plugin, String name) {
        return KitsAPI.getKit(plugin + "-" + name);
    }

    public Kit resolvePluginKit(String plugin, String name) {
        final String kitName = plugin + "-" + name;
        Kit kit = KitsAPI.getKit(kitName);

        if (kit != null) return kit;

        kit = new KitsBuilder(kitName).build();
        kit.save();
        return kit;
    }

    public Kit resolvePluginKit(JavaPlugin plugin, String name) {
        return resolvePluginKit(plugin.getName(), name);
    }
}
