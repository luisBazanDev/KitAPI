package pe.bazan.luis.plugins.kitapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.kitapi.utils.MessageFormater;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageFormater.formatMC("You don't a player."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length >= 1) {
            String arg0 = args[0];
            switch (arg0) {
                case "add":
                    return true;
                case "edit":
                    return true;
                case "save":

                    return true;
                case "set":
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
            String[] words = new String[]{"add", "edit", "save", "set"};

            for (String word : words) {
                if (word.startsWith(search)) complete.add(word);
            }
        }
        return complete;
    }
}
