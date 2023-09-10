package me.logan.proximitychat.Events;

import me.logan.proximitychat.ProximityChat;
import me.logan.proximitychat.manager.ConfigManager;
import me.logan.proximitychat.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class AsyncPlayerChat implements Listener {

    private final ConfigManager configManager;
    private final ItemStack megaphone;

    public AsyncPlayerChat(ProximityChat plugin) {
        this.configManager = plugin.getConfigManager();
        this.megaphone = plugin.getMegaphone();
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

        Bukkit.getOnlinePlayers().forEach(target -> {
            if(target.getUniqueId().equals(player.getUniqueId())) return;
            int distance = (int) player.getLocation().distance(target.getLocation());
            if(player.getInventory().getItemInMainHand().equals(megaphone)) {
                if(distance <= configManager.getMegaphoneRange()) {
                    if(configManager.showPlayerDistance()) {
                        target.sendMessage(ColorUtils.colorize("&8[&e" + distance + "m&8] &a" + player.getName() + " &8→ &7" + event.getMessage()));
                        return;
                    }
                    target.sendMessage(ColorUtils.colorize("&a" + player.getName() + " &8→ &7" + event.getMessage()));
                }
            } else {
                if(distance <= configManager.getTalkRange()) {
                    if(configManager.showPlayerDistance()) {
                        target.sendMessage(ColorUtils.colorize("&8[&e" + distance + "m&8] &a" + player.getName() + " &8→ &7" + event.getMessage()));
                        return;
                    }
                    target.sendMessage(ColorUtils.colorize("&a" + player.getName() + " &8→ &7" + event.getMessage()));
                }
            }
        });

        player.sendMessage(ColorUtils.colorize("&a&lYOU" + " &8→ &7" + event.getMessage()));
    }

}
