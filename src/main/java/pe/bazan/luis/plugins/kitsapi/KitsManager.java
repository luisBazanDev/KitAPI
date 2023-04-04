package pe.bazan.luis.plugins.kitsapi;

import pe.bazan.luis.plugins.kitsapi.configs.KitsConfig;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;

import java.util.HashMap;

public class KitsManager {
    private HashMap<String, Kit> kits;
    private KitsConfig kitsConfig;

    public KitsManager() {
        this.kitsConfig = new KitsConfig();
        this.kits = new HashMap<>();
    }

    public void reloadKits() {

    }

    public void saveKit(Kit kit) {
        kits.put(kit.getName(), kit);
        kitsConfig.saveKit(kit);
    }

    public KitsConfig getKitsConfig() {
        return kitsConfig;
    }
}
