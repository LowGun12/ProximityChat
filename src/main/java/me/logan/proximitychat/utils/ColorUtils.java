package me.logan.proximitychat.utils;

import org.bukkit.ChatColor;

public class ColorUtils {


    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
