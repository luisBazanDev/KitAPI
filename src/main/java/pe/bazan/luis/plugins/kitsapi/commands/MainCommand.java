package pe.bazan.luis.plugins.kitsapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.kitsapi.KitsAPI;
import pe.bazan.luis.plugins.kitsapi.utils.MessageFormater;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 2) {
            String arg0 = args[0];
            switch (arg0) {
                case "add":
                    new AddKitCommand(sender, args);
                    return true;
                case "set":
                    new SetKitCommand(sender, args);
                    return true;
                case "delete":
                    new DeleteKitCommand(sender, args);
                    return true;
                case "set-permanent":
                    new SetPermanentKit(sender, args);
                    return true;
                case "clear-permanent":
                    new ClearPermanentKit(sender, args);
                    return true;
            }
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageFormater.formatMC("You don't a player."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length >= 1) {
            String arg0 = args[0];
            switch (arg0) {
                case "edit":
                    new EditKitCommand(player, args);
                    return true;
                case "save":
                    new SaveKitCommand(player, args);
                    return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List <String> complete = new ArrayList<>();
        if (args.length == 1) {
            String search = args[0].toLowerCase();
            String[] words = new String[]{"add", "edit", "delete", "save", "set", "set-permanent", "clear-permanent"};

            for (String word : words) {
                if (word.startsWith(search)) complete.add(word);
            }
        }
        if (args.length == 2) {
            String search = args[1].toLowerCase();
            if (
                    args[0].equalsIgnoreCase("add")
                            || args[0].equalsIgnoreCase("edit")
                            || args[0].equalsIgnoreCase("delete")
                            || args[0].equalsIgnoreCase("save")
                            || args[0].equalsIgnoreCase("set")
                            || args[0].equalsIgnoreCase("set-permanent")
            ) {
                for (String kitName : KitsAPI.getInstance().getKitsManager().getKits().keySet()) {
                    if (kitName.startsWith(search)) complete.add(kitName);
                }
            } else if (args[0].equalsIgnoreCase("clear-permanent")) {
                if ("all".startsWith(search)) complete.add("all");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().toLowerCase().startsWith(search)) complete.add(player.getName());
                }
            }
        }
        if (args.length == 3) {
            String search = args[2].toLowerCase();
            if (
                    args[0].equalsIgnoreCase("add")
                            || args[0].equalsIgnoreCase("set")
                            || args[0].equalsIgnoreCase("set-permanent")
            ) {
                if ("all".startsWith(search)) complete.add("all");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().toLowerCase().startsWith(search)) complete.add(player.getName());
                }
            }
        }
        return complete;
    }
}
