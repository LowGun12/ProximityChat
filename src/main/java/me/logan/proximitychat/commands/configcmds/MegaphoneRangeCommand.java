package me.logan.proximitychat.commands.configcmds;

import me.logan.proximitychat.ProximityChat;
import me.logan.proximitychat.utils.ColorUtils;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MegaphoneRangeCommand implements CommandExecutor, TabCompleter{

    private ProximityChat plugin;


    public MegaphoneRangeCommand(ProximityChat plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("proximitychat.toggleglobalchat")) {

                if (args.length != 1) {
                    player.sendMessage(ColorUtils.colorize("&cInvalid usage, /smpr <range>"));
                } else {

                    try {
                        int newRange = Integer.parseInt(args[0]);
                        plugin.getConfig().set("megaphone-range", newRange);
                        plugin.saveConfig();
                        player.sendMessage(ColorUtils.colorize("&aMegaphone range has been set to " + newRange));
                    } catch (NumberFormatException e ) {
                        player.sendMessage(ColorUtils.colorize("&cArgument must be a number"));
                    }
                }

            } else {
                player.sendMessage(ColorUtils.colorize("&cYou do not have permission to use this command."));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

}
