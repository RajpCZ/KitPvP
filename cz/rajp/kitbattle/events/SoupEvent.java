package cz.rajp.kitbattle.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupEvent implements Listener {

    @EventHandler
    public void onSoup(PlayerInteractEvent e) {
        Player hrac = e.getPlayer();
        Location loc = hrac.getLocation();
        if (hrac.getItemInHand().getType() == Material.MUSHROOM_SOUP && (
                e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {

                double maxhealth = hrac.getMaxHealth();
                double health = hrac.getHealth();
                double hos = 7;
                double newhealth = health + hos;
                double mh7 = maxhealth - hos;


                if (hrac.getHealth() > mh7) {

                    if (health == maxhealth) {
                        e.setCancelled(true);
                    } else {
                        hrac.setHealth(maxhealth);
                        hrac.getItemInHand().setType(Material.BOWL);
                        hrac.playSound(loc, Sound.BURP, 1.0F, 1.0F);
                    }
                } else {
                    hrac.setHealth(newhealth);
                    hrac.getItemInHand().setType(Material.BOWL);
                    hrac.playSound(loc, Sound.BURP, 1.0F, 1.0F);
            }
        }
    }
}
