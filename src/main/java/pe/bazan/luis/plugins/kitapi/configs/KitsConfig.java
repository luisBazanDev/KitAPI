package pe.bazan.luis.plugins.kitapi.configs;

import pe.bazan.luis.plugins.kitapi.KitAPI;
import pe.bazan.luis.plugins.kitapi.instances.Kit;

import java.util.ArrayList;
import java.util.List;

public class KitsConfig {
    private CustomConfig customConfig = new CustomConfig(KitAPI.getInstance(), "kits.yml");
    private List<Kit> kits = new ArrayList();

    public CustomConfig getCustomConfig() {
        return customConfig;
    }
}
