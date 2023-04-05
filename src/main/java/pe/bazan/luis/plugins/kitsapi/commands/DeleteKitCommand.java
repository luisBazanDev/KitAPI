package pe.bazan.luis.plugins.kitsapi.commands;

import org.bukkit.command.CommandSender;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.instances.Kit;
import pe.bazan.luis.plugins.kitsapi.utils.MessageFormater;

public class DeleteKitCommand {
    public DeleteKitCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessageFormater.formatMC("Use: /kitsapi delete <kit-name>"));
            return;
        }
        Kit kit = KitsAPI.getKit(args[1]);

        if (kit == null) {
            sender.sendMessage(MessageFormater.formatMC("Kit not found"));
            return;
        }

        KitsAPI.getManager().deleteKit(kit.getName());
        sender.sendMessage(MessageFormater.formatMC(String.format("The kit \"%s\" has been successfully deleted.", kit.getName())));
    }
}
