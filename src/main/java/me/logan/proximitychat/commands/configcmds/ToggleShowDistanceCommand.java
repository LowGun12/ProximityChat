package me.logan.proximitychat.commands.configcmds;

import me.logan.proximitychat.ProximityChat;
import me.logan.proximitychat.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ToggleShowDistanceCommand implements CommandExecutor, TabExecutor {


    private ProximityChat plugin;

    public ToggleShowDistanceCommand(ProximityChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("proximitychat.toggledistance")) {

                if (args.length != 1) {
                    player.sendMessage(ColorUtils.colorize("&cInvalid usage, /tsd <true/false>"));
                } else {
                    String state = args[0].toLowerCase();

                    if (state.equals("true") || state.equals("false")) {
                        boolean currentState = plugin.getConfig().getBoolean("show-distance");

                        if (currentState && state.equals("true") || !currentState && state.equals("false")) {
                            player.sendMessage(ColorUtils.colorize("&cGlobal chat usage is already set to " + state));
                        } else {
                            plugin.getConfig().set("show-distance", Boolean.valueOf(state));
                            plugin.saveConfig();
                            player.sendMessage(ColorUtils.colorize("&aMessage distance is now set to " + state));
                        }
                    } else {
                        player.sendMessage(ColorUtils.colorize("&cMust be a true or false value"));
                    }
                }
            } else {
                player.sendMessage(ColorUtils.colorize("&cYou do not have permission to use this command."));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String currentArg = args[0].toLowerCase();

            if ("true".startsWith(currentArg)) {
                completions.add("true");
            }
            if ("false".startsWith(currentArg)) {
                completions.add("false");
            }
        }

        return completions;
    }
}
