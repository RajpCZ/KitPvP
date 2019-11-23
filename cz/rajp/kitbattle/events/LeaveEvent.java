package cz.rajp.kitbattle.events;

import cz.rajp.kitbattle.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

public class LeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player hrac = e.getPlayer();
        e.setQuitMessage(null);

        Main.getInstance().killstreaks.remove(hrac.getName());
        for (PotionEffect effect : hrac.getActivePotionEffects())
            hrac.removePotionEffect(effect.getType());

        if (Main.getInstance().kitused.contains(hrac.getName()) || Main.getInstance().defaultt.contains(hrac.getName())
                || Main.getInstance().tank.contains(hrac.getName())) {

            Main.getInstance().kitused.remove(hrac.getName());
            Main.getInstance().defaultt.remove(hrac.getName());
            Main.getInstance().tank.remove(hrac.getName());

        }

    }
}
