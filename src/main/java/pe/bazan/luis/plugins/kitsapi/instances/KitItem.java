package pe.bazan.luis.plugins.kitsapi.instances;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.Logger;
import pe.bazan.luis.plugins.kitsapi.utils.ItemSerialize;

import javax.annotation.Nullable;

public class KitItem {
    private final int slot;
    private final String data;

    public KitItem(int slot, String data) {
        this.slot = slot;
        this.data = data;
    }

    public KitItem(int slot, ItemStack item) {
        this.slot = slot;
        this.data = ItemSerialize.serialize(item);
    }

    public void setItem(Player player) {
        final ItemStack itemStack = ItemSerialize.deserialize(data);
        if(itemStack == null) {
            Logger.error(String.format("Not deserialized: Slot - %s, Data - %s", slot, data));
            return;
        }
        player.getInventory().setItem(slot, itemStack);
    }

    public void addItem(Player player) {
        final ItemStack itemStack = ItemSerialize.deserialize(data);
        if(itemStack == null) {
            Logger.error(String.format("Not deserialized: Data - %s", data));
            return;
        }
        if (player.getInventory().getItem(slot) == null) {
            player.getInventory().setItem(slot, itemStack);
        } else {
            player.getInventory().addItem(itemStack);
        }
    }

    @Nullable
    public ItemStack getItem() {
        final ItemStack itemStack = ItemSerialize.deserialize(data);
        if(itemStack == null) {
            Logger.error(String.format("Not deserialized: Slot - %s, Data - %s", slot, data));
            return null;
        }
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public String getData() {
        return data;
    }
}
