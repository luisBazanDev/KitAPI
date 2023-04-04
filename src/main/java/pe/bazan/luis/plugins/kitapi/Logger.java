package pe.bazan.luis.plugins.kitapi;

import pe.bazan.luis.plugins.kitapi.utils.MessageFormater;

public class Logger {
    public static void error(String message) {
        KitAPI.getInstance().getLogger().info(MessageFormater.formatTxt("&cError: "+message));
    }
}
