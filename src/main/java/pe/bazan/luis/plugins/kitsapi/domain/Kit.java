package pe.bazan.luis.plugins.kitsapi.domain;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.Logger;

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

    public Kit(String name, ConfigurationSection config) {
        this.name = name;

        for (String key : config.getKeys(false)) {
            try {
                int slot = Integer.parseInt(key);
                this.items.add(new KitItem(slot, config.getString(key)));
            } catch (NumberFormatException e) {
                Logger.error(String.format("Error on parse slot id on kit \"%s\" slot: %s", name, key));
            }
        }
    }

    public void setItems(Player player) {
        player.getInventory().clear();
        for (KitItem item : items) {
            item.setItem(player);
        }
    }

    public void setName(String name) {
        this.name = name;
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
