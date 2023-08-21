package pe.bazan.luis.plugins.kitsapi.tasks;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.domain.Kit;
import pe.bazan.luis.plugins.kitsapi.domain.KitItem;

public class PlayerTracker implements Runnable {
    final private Player player;
    private Kit kit;
    private int taskId;

    public PlayerTracker(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;
    }

    @Override
    public void run() {
        if (!player.isOnline()) return;
        int[] slots = new int[player.getInventory().getContents().length];
        for (KitItem kitItem : kit.getItems()) {
            final ItemStack itemStack = player.getInventory().getItem(kitItem.getSlot());
            slots[kitItem.getSlot()] = 1;
            if (itemStack == null) continue;
            if (itemStack.equals(kitItem.getItem())) continue;
            kitItem.setItem(player);
        }
        for (int s = 0; s < slots.length; s++) {
            if (slots[s] == 1) continue;
            if (player.getInventory().getItem(s) != null)
                player.getInventory().setItem(s, null);
        }
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
