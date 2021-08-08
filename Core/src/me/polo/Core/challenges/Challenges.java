package me.polo.Core.challenges;

import me.polo.Core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Challenges implements Listener, CommandExecutor {

    private Plugin plugin = Core.getPlugin(Core.class);

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player){
        Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("challenges")){
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Challenge Menu");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "/ice for ice challenge");
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(" ");

            }
        }
        return true;
    }

}