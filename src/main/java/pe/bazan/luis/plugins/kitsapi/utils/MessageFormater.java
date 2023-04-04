package pe.bazan.luis.plugins.kitsapi.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

public class MessageFormater {
    public static Component formatMC(String text) {
        return Component.text(formatTxt(text));
    }

    public static String formatTxt(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
