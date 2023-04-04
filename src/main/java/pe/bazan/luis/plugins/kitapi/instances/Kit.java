package pe.bazan.luis.plugins.kitapi.instances;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Kit {
    private final List<KitItem> items = new ArrayList<>();
    private String name;

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

    public List<KitItem> getItems() {
        return items;
    }
}
