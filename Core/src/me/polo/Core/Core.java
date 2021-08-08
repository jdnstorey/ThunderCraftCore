package me.polo.Core;

import me.polo.Core.admin.ClearChat;
import me.polo.Core.admin.HealFeed;
import me.polo.Core.admin.Freeze;
import me.polo.Core.challenges.Challenges;
import me.polo.Core.challenges.Ice;
import me.polo.Core.trades.TradeCommand;
import me.polo.Core.trades.TradeListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public static Core plugin;

    public void onEnable(){
        plugin = this;

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Core Enabled!");

        getServer().getPluginCommand("challenges").setExecutor(new Challenges()); // REMOVE THIS TO REMOVE ALL CHALLENGES
        getServer().getPluginCommand("ice").setExecutor(new Ice(this)); // REMOVE THIS ONCE THE CHALLENGE IS DONE

        getServer().getPluginCommand("clearchat").setExecutor(new ClearChat());
        getServer().getPluginCommand("heal").setExecutor(new HealFeed());
        getServer().getPluginCommand("feed").setExecutor(new HealFeed());
        getServer().getPluginCommand("freeze").setExecutor(new Freeze());
        getServer().getPluginCommand("unfreeze").setExecutor(new Freeze());

        TradeListener trader = new TradeListener();
        getServer().getPluginCommand("trade").setExecutor(new TradeCommand(trader));
        getServer().getPluginManager().registerEvents(trader, this);

        getServer().getPluginManager().registerEvents(new Freeze(),this);
        getServer().getPluginManager().registerEvents(new Ice(this), this);
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Core Disabled!");
    }

}
