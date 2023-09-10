package me.logan.proximitychat;

import me.logan.proximitychat.Events.AsyncPlayerChat;
import me.logan.proximitychat.commands.GiveMegaphoneCommand;
import me.logan.proximitychat.commands.GlobalChatCommand;
import me.logan.proximitychat.commands.configcmds.AllowGlobalChatCommand;
import me.logan.proximitychat.commands.configcmds.MegaphoneRangeCommand;
import me.logan.proximitychat.manager.ConfigManager;
import me.logan.proximitychat.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.Objects;


public final class ProximityChat extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(this), this);
        Objects.requireNonNull(getCommand("gmp")).setExecutor(new GiveMegaphoneCommand(getMegaphone()));
        Objects.requireNonNull(getCommand("gc")).setExecutor(new GlobalChatCommand(getConfigManager()));
        getCommand("allowglobalchat").setExecutor(new AllowGlobalChatCommand(this));
        getCommand("setmegaphonerange").setExecutor(new MegaphoneRangeCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ItemStack getMegaphone() {
        ItemStack megaphone = new ItemStack(Material.STICK);
        ItemMeta megaphoneMeta = megaphone.getItemMeta();

        if(megaphoneMeta != null) {
            megaphoneMeta.setDisplayName(ColorUtils.colorize("&a&lMegaphone"));
            megaphoneMeta.setLore(Collections.singletonList(ColorUtils.colorize("&7Increases your talk rang to &e"
                    + getConfigManager().getMegaphoneRange()
                    + " &7blocks!")));
            megaphone.setItemMeta(megaphoneMeta);
        }

        return megaphone;
    }

}
