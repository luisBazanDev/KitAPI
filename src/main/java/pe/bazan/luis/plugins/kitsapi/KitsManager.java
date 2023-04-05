package pe.bazan.luis.plugins.kitsapi;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.configs.KitsConfig;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;

import javax.annotation.Nullable;
import java.util.HashMap;

public class KitsManager {
    private HashMap<String, Kit> kits;
    private KitsConfig kitsConfig;

    public KitsManager() {
        this.kitsConfig = new KitsConfig();
        reloadKits();
    }

    public void reloadKits() {
        kits = new HashMap<>();
        kitsConfig.getCustomConfig().reload();
        for (String kitName : kitsConfig.getCustomConfig().getConfig().getConfigurationSection("kits").getKeys(false)) {
            ConfigurationSection kitConfig = kitsConfig.getKit(kitName);
            kits.put(kitName, new Kit(kitName, kitConfig));
        }
    }

    public void saveKit(Kit kit) {
        kits.put(kit.getName(), kit);
        kitsConfig.saveKit(kit);
    }

    public void addKit(String name, HashMap<Integer, ItemStack> items) {
        Kit kit = new Kit(name, items);
        kit.save();

        kits.put(name, kit);
    }

    public @Nullable Kit getKit(String name) {
        return kits.get(name);
    }

    public void deleteKit(String name) {
        if (!kits.containsKey(name)) return;
        kits.remove(name);
        kitsConfig.deleteKit(name);
    }

    public KitsConfig getKitsConfig() {
        return kitsConfig;
    }

    public HashMap<String, Kit> getKits() {
        return kits;
    }
}
