package pe.bazan.luis.plugins.kitsapi.configs;

import org.bukkit.configuration.ConfigurationSection;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;
import pe.bazan.luis.plugins.kitsapi.instances.KitItem;

import javax.annotation.Nullable;

public class KitsConfig {
    private final CustomConfig customConfig;

    public KitsConfig() {
        this.customConfig = new CustomConfig(KitsAPI.getInstance(), "kits.yml");
    }

    public @Nullable ConfigurationSection getKit(String name) {
        return customConfig.getConfig().getConfigurationSection("kits."+name);
    }

    public void saveKit(Kit kit) {
        ConfigurationSection kits = customConfig.getConfig().getConfigurationSection("kits");

        if (kits == null) kits = customConfig.getConfig().createSection("kits");

        ConfigurationSection kitSection = kits.createSection(kit.getName());

        for (KitItem kitItem : kit.getItems()) {
            kitSection.set(String.valueOf(kitItem.getSlot()), kitItem.getData());
        }

        customConfig.save();
    }

    public void deleteKit(String name) {
        ConfigurationSection kits = customConfig.getConfig().getConfigurationSection("kits");
        kits.set(name, null);
        customConfig.save();
    }

    public CustomConfig getCustomConfig() {
        return customConfig;
    }
}
