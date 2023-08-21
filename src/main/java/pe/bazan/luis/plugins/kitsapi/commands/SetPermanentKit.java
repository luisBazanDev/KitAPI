package pe.bazan.luis.plugins.kitsapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.api.KitsHelper;
import pe.bazan.luis.plugins.kitsapi.domain.Kit;
import pe.bazan.luis.plugins.kitsapi.utils.MessageFormater;

public class SetPermanentKit {
    public SetPermanentKit(CommandSender sender, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(MessageFormater.formatMC("Use: /kitsapi set-permanent <kit-name> <player/all>"));
            return;
        }
        Kit kit = KitsAPI.getKit(args[1]);
        Player player = Bukkit.getPlayer(args[2]);

        if (kit == null) {
            sender.sendMessage(MessageFormater.formatMC("Kit not found"));
            return;
        }

        if (player == null && !args[2].equalsIgnoreCase("all")) {
            sender.sendMessage(MessageFormater.formatMC("Player not found"));
            return;
        }

        if (args[2].equalsIgnoreCase("all")) {
            for (Player playerOnline : Bukkit.getOnlinePlayers()) {
                KitsHelper.getKitManager(KitsAPI.getInstance()).setPermanentKit(playerOnline, kit);
            }
            sender.sendMessage(MessageFormater.formatMC(String.format("Set items correctly from %s kit for the all players.", kit.getName())));
        } else {
            KitsHelper.getKitManager(KitsAPI.getInstance()).setPermanentKit(player, kit);
            sender.sendMessage(MessageFormater.formatMC(String.format("Set items correctly from %s kit for %s", kit.getName(), player.getName())));
        }
    }
}
