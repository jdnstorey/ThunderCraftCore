package me.polo.Core.trades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class TradeListener implements Listener {

    public HashMap<Player, Player> tradingPlayers = new HashMap<Player, Player>();

    public void addPlayersToTradeList(Player p1, Player p2){
        tradingPlayers.put(p1, p2);
    }

    @EventHandler
    public void onPlayerInventoryClick (InventoryClickEvent e){
        if(e.getView().getTitle().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "TRADE MENU")){
            Player p = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();
            if(tradingPlayers.containsKey(p)){
                if(item.getType() == Material.RED_CONCRETE || item.getType() == Material.GREEN_CONCRETE){
                    e.setCancelled(true);
                    accept(p, e.getCurrentItem());
                }
            }
        }
    }

    @EventHandler
    public void accept(Player p, ItemStack item) {
        if(item.getType().equals(Material.RED_CONCRETE)){
            item.setType(Material.GREEN_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(p.getName());
            item.setItemMeta(meta);
        } else if(item.getType().equals(Material.GREEN_CONCRETE)){
            if(item.getItemMeta().getDisplayName().equals(p.getName())){
                item.setType(Material.RED_CONCRETE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(null);
                item.setItemMeta(meta);
            } else {
                finishTrade(p.getOpenInventory().getTopInventory());
            }
        }
    }

    public void finishTrade(Inventory inv){
        List<HumanEntity> viewers = inv.getViewers();
        Player p1;
        Player p2;
        if(tradingPlayers.containsKey((Player) viewers.get(0))){
            p1 = (Player) viewers.get(0);
            p2 = (Player) viewers.get(1);
        } else {
            p2 = (Player) viewers.get(0);
            p1 = (Player) viewers.get(1);
        }
        p1.closeInventory();
        p2.closeInventory();
        for(int i = 0; i<9; i++){
            if(!inv.getItem(i).equals(null)){
                p2.getInventory().addItem(inv.getItem(i));
            }
            if(!inv.getItem(i+18).equals(null)){
                p1.getInventory().addItem(inv.getItem(i+18));
            }
        }
        tradingPlayers.remove(p1);
    }
}
