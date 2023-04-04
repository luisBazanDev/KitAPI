package pe.bazan.luis.plugins.kitsapi.instances;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kit {
    private final List<KitItem> items = new ArrayList<>();
    private String name;

    public Kit(String name, HashMap<Integer, ItemStack> items) {
        this.name = name;
        items.forEach((k,v) -> {
            this.items.add(new KitItem(k, v));
        });
    }

    public void setItems(Player player) {
        for (KitItem item : items) {
            item.setItem(player);
        }
    }

    public void addItems(Player player) {
        for (KitItem item : items) {
            item.addItem(player);
        }
    }

    public void save() {
        KitsAPI.getInstance().getKitsManager().saveKit(this);
    }

    public List<KitItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
}
