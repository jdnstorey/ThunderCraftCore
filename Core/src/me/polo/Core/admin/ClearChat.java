package me.polo.Core.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("clearchat")) {
                if (p.hasPermission("chat.clear")) {
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage("-=-=-=-=-=-=-=-=-=-=-=-");
                    Bukkit.broadcastMessage("Chat Cleared By " + p.getName());
                    Bukkit.broadcastMessage("-=-=-=-=-=-=-=-=-=-=-=-");
                } else {
                    p.sendMessage(ChatColor.RED + "Insufficient Permission");
                }
            }
        }
        return false;
    }
}