package me.logan.proximitychat.commands;

import me.logan.proximitychat.manager.ConfigManager;
import me.logan.proximitychat.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalChatCommand implements CommandExecutor {

    private final ConfigManager configManager;

    public GlobalChatCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!configManager.allowGlobalChat()) {
            sender.sendMessage(ColorUtils.colorize("&cGlobal Chat is disabled"));
            return false;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.colorize("&eThis command is only for players!"));
            return false;
        }

        Player player = (Player) sender;

        if(args.length < 1) {
            sender.sendMessage(ColorUtils.colorize("&7Invalid usage.. Try &e/gc <message>&7."));
            return false;
        }

        StringBuilder builder = new StringBuilder();
        for (String word : args) {
            builder.append(word).append(" ");
        }

        Bukkit.broadcastMessage(ColorUtils.colorize("&8[&5GC&8] &a" + player.getName() + " &8→ &7" + builder.toString().trim()));
        return true;
    }
}