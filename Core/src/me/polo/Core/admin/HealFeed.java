package me.polo.Core.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealFeed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("heal") || cmd.getName().equalsIgnoreCase("h")) {
                Player p = (Player) sender;

                if(p.hasPermission("heal.use")) {
                    if (args.length == 0) {
                        p.setHealth(20);
                        p.sendMessage(ChatColor.GREEN + "Healed!");
                    } else if (args.length == 1) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t != null) {
                            t.setHealth(20);
                            t.sendMessage(ChatColor.GREEN + "Healed!");
                            p.sendMessage(ChatColor.GREEN + "Healed " + t.getName());
                        } else {
                            p.sendMessage("Uh oh. Try again!");
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Insufficient Permission");
                }
            }

            if (cmd.getName().equalsIgnoreCase("feed") || cmd.getName().equalsIgnoreCase("f")) {
                Player p = (Player) sender;

                if(p.hasPermission("feed.use")) {
                    if (args.length == 0) {
                        p.setFoodLevel(20);
                        p.sendMessage(ChatColor.GREEN + "Fed!");
                    } else if (args.length == 1) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t != null) {
                            t.setFoodLevel(20);
                            t.sendMessage(ChatColor.GREEN + "Fed!");
                            p.sendMessage(ChatColor.GREEN + "Fed " + t.getName());
                        } else {
                            p.sendMessage("Uh oh. Try again!");
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Insufficient Permission");
                }
            }
        }
        return false;
    }
}