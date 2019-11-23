package cz.rajp.kitbattle.events;

import cz.rajp.kitbattle.Main;
import cz.rajp.kitbattle.game.KitManager;
import cz.rajp.kitbattle.game.ShopManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemClick implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if (e.getItem() == null)
            return;
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                e.getItem().getType() == Material.EYE_OF_ENDER) {
            Player hrac = e.getPlayer();

            e.setCancelled(true);
            KitManager.openKitMenu(hrac);
        } else if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                e.getItem().getType() == Material.CHEST) {
            Player hrac = e.getPlayer();

            e.setCancelled(true);
            ShopManager.openShopMenu(hrac);
        }
    }
}

