package cz.rajp.kitbattle.events;

import cz.rajp.kitbattle.Main;
import cz.rajp.kitbattle.game.StatsManager;
import cz.rajp.kitbattle.utils.ChatUtil;
import net.minecraft.server.v1_8_R1.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player hrac = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        e.getDrops().clear();
        if (Main.getInstance().kitused.contains(hrac.getName()) || Main.getInstance().defaultt.contains(hrac.getName())
        || Main.getInstance().tank.contains(hrac.getName())) {

            Main.getInstance().kitused.remove(hrac.getName());
            Main.getInstance().defaultt.remove(hrac.getName());
            Main.getInstance().tank.remove(hrac.getName());

            //MORE KITS

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                public void run() {
                    hrac.setCanPickupItems(false);
                    hrac.spigot().respawn();
                }
            }, 1);

            hrac.performCommand("spawn123456789");

            e.setDeathMessage(Main.getInstance().prefix + "§3" + hrac.getName() + " §7has been killed by §b" + hrac.getKiller().getDisplayName());
            hrac.sendMessage(Main.getInstance().prefix + "§7You have been killed by §3" + killer.getName());

            if (Main.getInstance().killstreaks.get(hrac.getName()) >= 1) {
                Main.getInstance().killstreaks.put(hrac.getName(), 0);
            }

            StatsManager.addKill(killer, 1);
            StatsManager.addDeath(hrac, 1);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Player hrac = e.getEntity().getKiller();

        Main.getInstance().killstreaks.put(hrac.getName(), Main.getInstance().killstreaks.get(hrac.getName()) + 1);

        if (Main.getInstance().killstreaks.get(hrac.getName()) == 3) {
            int killstreak = 3;
            Bukkit.broadcastMessage(Main.getInstance().prefix + "§3" + hrac.getName() + " §7has a killstreak of §b" + killstreak);
            if (hrac.hasPermission("kitpvp.booster")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins add " + hrac.getName() + " 15");
                hrac.sendMessage("§e+15 coins for killstreak! §8(§f50% Bonus§8)");
            } else {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins add " + hrac.getName() + " 10");
                hrac.sendMessage("§e+10 coins for killstreak!");
            }
        }

        if (Main.getInstance().killstreaks.get(hrac.getName()) == 5) {
            int killstreak = 5;
            Bukkit.broadcastMessage(Main.getInstance().prefix + "§3" + hrac.getName() + " §7has a killstreak of §b" + killstreak);
            if (hrac.hasPermission("kitpvp.booster")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins add " + hrac.getName() + " 30");
                hrac.sendMessage("§e+30 coins for killstreak! §8(§f50% Bonus§8)");
            } else {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins add " + hrac.getName() + " 20");
                hrac.sendMessage("§e+20 coins for killstreak!");
            }
        }

        if (hrac.hasPermission("kitpvp.booster")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins add " + hrac.getName() + " 3");
            hrac.sendMessage("§e+3 coins for kill! §8(§f50% Bonus§8)");
        } else {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "coins add " + hrac.getName() + " 2");
            hrac.sendMessage("§e+2 coins for kill!");
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player hrac = e.getPlayer();

        if (!Main.getInstance().kitused.contains(hrac.getName()) || !Main.getInstance().defaultt.contains(hrac.getName())
                || !Main.getInstance().tank.contains(hrac.getName())) {

            ItemStack kit_selector = new ItemStack(Material.EYE_OF_ENDER);
            ItemMeta meta = kit_selector.getItemMeta();
            meta.setDisplayName("§b§lKit Selector");
            kit_selector.setItemMeta(meta);
            hrac.getInventory().setItem(0, kit_selector);

            ItemStack shop = new ItemStack(Material.CHEST);
            ItemMeta smeta = shop.getItemMeta();
            smeta.setDisplayName("§b§lShop");
            shop.setItemMeta(smeta);
            hrac.getInventory().setItem(1, shop);
        }
    }
}
