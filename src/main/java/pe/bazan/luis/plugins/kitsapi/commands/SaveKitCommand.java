package pe.bazan.luis.plugins.kitsapi.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.KitsManager;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;
import pe.bazan.luis.plugins.kitsapi.utils.MessageFormater;

import java.util.HashMap;

public class SaveKitCommand {
    public SaveKitCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(MessageFormater.formatMC("Insert a name for the kit"));
            return;
        }
        String kitName = args[1];
        HashMap<Integer, ItemStack> items = new HashMap<>();
        for (int i = 0; i <= 45; i++) {
            if (player.getInventory().getItem(i) == null) continue;
            items.put(i, player.getInventory().getItem(i));
        }
        KitsAPI.getInstance().getKitsManager().addKit(kitName, items);
        player.sendMessage(MessageFormater.formatMC("&aKit was saved correctly"));
    }
}
