package pe.bazan.luis.plugins.kitsapi;

import pe.bazan.luis.plugins.kitsapi.utils.MessageFormater;

public class Logger {
    public static void error(String message) {
        KitsAPI.getInstance().getLogger().info(MessageFormater.formatTxt("&cError: "+message));
    }
}
