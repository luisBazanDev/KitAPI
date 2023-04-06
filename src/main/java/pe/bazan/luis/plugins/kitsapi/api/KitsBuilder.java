package pe.bazan.luis.plugins.kitsapi.api;

import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;

import java.util.HashMap;

public class KitsBuilder {
    private String name;
    private HashMap<Integer, ItemStack> items;
    private Kit kit;

    public KitsBuilder(String name) {
        this.name = name;
        this.items = new HashMap<>();
    }

    public KitsBuilder setItem(int slot, ItemStack item) {
        items.put(slot, item);
        return this;
    }

    public void registerDefaultKit() {
        KitsAPI.getManager().saveKit(build());
    }

    public Kit build() {
        kit = new Kit(name, items);
        return kit;
    }
}
