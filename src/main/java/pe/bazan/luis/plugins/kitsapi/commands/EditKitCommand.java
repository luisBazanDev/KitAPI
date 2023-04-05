package pe.bazan.luis.plugins.kitsapi.commands;

import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;
import pe.bazan.luis.plugins.kitsapi.utils.MessageFormater;

public class EditKitCommand {
    public EditKitCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(MessageFormater.formatMC("Insert a kit's name"));
            return;
        }
        String kitName = args[1];

        Kit kit = KitsAPI.getInstance().getKitsManager().getKit(kitName);

        if (kit == null) {
            player.sendMessage(MessageFormater.formatMC("Kit not found"));
            return;
        }

        kit.setItems(player);
    }
}
