package cz.rajp.kitbattle.events;

import cz.rajp.kitbattle.utils.CoinsCMD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if ((e.getCurrentItem() != null) && e.getCurrentItem().getItemMeta() != null) {
            Inventory inv = e.getInventory();
            Player hrac = (Player) e.getWhoClicked();
            if (inv.getTitle().equals("§bKit Selector")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Default")) {
                    Bukkit.dispatchCommand(hrac, "default");
                    e.setCancelled(true);
                    hrac.closeInventory();
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Tank")) {
                    if (hrac.hasPermission("kitpvp.tank")) {
                        Bukkit.dispatchCommand(hrac, "tank");
                        e.setCancelled(true);
                        hrac.closeInventory();
                    } else {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins remove " + hrac.getName() + " 899");
                        e.setCancelled(true);
                        hrac.closeInventory();
                    }
                }
            }
        }

    }
}
