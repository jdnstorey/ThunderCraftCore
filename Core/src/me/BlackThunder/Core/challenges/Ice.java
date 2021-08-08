package me.BlackThunder.Core.challenges;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Ice implements Listener, CommandExecutor{

    private JavaPlugin plugin;
    public Ice(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    ArrayList<Player> cooldown = new ArrayList<Player>();

    public ItemStack icepick() {
        ItemStack ice = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta icemeta = ice.getItemMeta();
        icemeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Ice Breaker");
        icemeta.setUnbreakable(true);
        icemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        ice.setItemMeta(icemeta);
        return ice;
    }

    public ItemStack frame() {
        ItemStack frame = new ItemStack(Material.ITEM_FRAME);
        ItemMeta framemeta = frame.getItemMeta();
        framemeta.setDisplayName("Place Pickaxe in Here when not using");
        frame.setItemMeta(framemeta);
        return frame;
    }

    public int time;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("ice")){
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("settime")){
                    int days = Integer.parseInt(args[1]);
                    time = 1728000 /* 1day */ * days;
                }
            }
            if (cooldown.contains(p)) {

                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You already have the pickaxe!");
                p.sendMessage(" ");
                p.sendMessage(" ");

            } else if (!(p.getInventory().contains(icepick()))) {
                p.getInventory().addItem(icepick());
                p.getInventory().addItem(frame());

                cooldown.add(p);

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> cooldown.remove(p), time);

            } else {
                p.sendMessage(" ");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You already have the pickaxe!");
                p.sendMessage(" ");
                p.sendMessage(" ");

                cooldown.add(p);
            }
        }
        return false;
    }



    @EventHandler
    @SuppressWarnings("deprecated")
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(player.getInventory().getItemInHand().equals(icepick())){
            Block block = e.getBlock();
            if(block.getType().equals(Material.ICE) || block.getType().equals(Material.BLUE_ICE) || block.getType().equals(Material.PACKED_ICE)){
            } else {
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "THIS PICKAXE IS FOR ICE ONLY");
            }
        }
    }
}
