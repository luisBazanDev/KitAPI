package pe.bazan.luis.plugins.kitsapi.api;

import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;

import javax.annotation.Nullable;

public class KitsHelper {
    public void registerPluginKit(JavaPlugin plugin, Kit kit) {
        kit.setName(plugin.getName() + "-" + kit.getName());
        KitsAPI.getManager().saveKit(kit);
    }

    public @Nullable Kit getPluginKit(JavaPlugin plugin, String name) {
        return KitsAPI.getKit(plugin.getName() + "-" + name);
    }

    public Kit resolvePluginKit(JavaPlugin plugin, String name) {
        final String kitName = plugin.getName() + "-" + name;
        Kit kit = KitsAPI.getKit(kitName);

        if (kit != null) return kit;

        kit = new KitsBuilder(kitName).build();
        kit.save();
        return kit;
    }
}
