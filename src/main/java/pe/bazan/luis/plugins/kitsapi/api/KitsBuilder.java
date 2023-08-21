package pe.bazan.luis.plugins.kitsapi.api;

import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;
import pe.bazan.luis.plugins.kitsapi.instances.KitItem;

import java.util.HashMap;

public class KitsBuilder {
    private String name;
    private HashMap<Integer, ItemStack> items;
    private Kit kit;

    public KitsBuilder(String name) {
        this.name = name;
        this.items = new HashMap<>();
    }

    public KitsBuilder(String name, Kit baseKit) {
        this.name = name;
        this.items = new HashMap<>();
        for (KitItem kitItem : baseKit.getItems()) {
            items.put(kitItem.getSlot(), kitItem.getItem());
        }
    }

    public KitsBuilder setItem(int slot, ItemStack item) {
        items.put(slot, item);
        return this;
    }

    public KitsBuilder setItem(KitItem kitItem) throws Exception {
        if (kitItem.getItem() == null) {
            throw new Exception("Kit Item is invalid");
        }
        items.put(kitItem.getSlot(), kitItem.getItem());
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
