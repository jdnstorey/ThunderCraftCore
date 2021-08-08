package me.BlackThunder.Core.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public class Freeze implements CommandExecutor, Listener {


    private JavaPlugin plugin;
    public Freeze(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public Freeze() {}

    static HashSet<UUID> frozen = new HashSet<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("freeze.use")) {
                if(cmd.getName().equalsIgnoreCase("freeze")) {
                    if (args.length == 0) {
                        p.sendMessage("Try /freeze <player>");
                    } else if (args.length == 1) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t != null) {
                            frozen.add(t.getUniqueId());

                            t.sendMessage(" ");
                            t.sendMessage(ChatColor.RED + "You have been frozen!");
                            t.sendMessage(" ");

                            p.sendMessage(" ");
                            p.sendMessage(ChatColor.GREEN + "You have frozen " + t.getName());
                            p.sendMessage(" ");
                        } else {
                            p.sendMessage(ChatColor.RED + "Player not found!");
                        }
                    }
                }
                if(cmd.getName().equalsIgnoreCase("unfreeze")) {
                    if (args.length == 0) {
                        p.sendMessage("Try /unfreeze <player>");
                    } else if (args.length == 1) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t != null) {
                            frozen.remove(t.getUniqueId());

                            t.sendMessage(" ");
                            t.sendMessage(ChatColor.RED + "You have been unfrozen!");
                            t.sendMessage(" ");

                            p.sendMessage(" ");
                            p.sendMessage(ChatColor.GREEN + "You have unfrozen " + t.getName());
                            p.sendMessage(" ");
                        } else {
                            p.sendMessage(ChatColor.RED + "Player not found!");
                        }
                    }
                }
            } else {
                p.sendMessage(ChatColor.RED + "Insufficient Permission");
            }
        }
        return false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(frozen.contains(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
