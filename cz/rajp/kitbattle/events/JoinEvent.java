package cz.rajp.kitbattle.events;

import cz.rajp.kitbattle.Main;
import cz.rajp.kitbattle.game.StatsManager;
import cz.rajp.kitbattle.utils.ChatUtil;
import cz.rajp.kitbattle.utils.CoinsAPI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JoinEvent implements Listener {

    public static ArrayList<Player> players = new ArrayList<Player>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player hrac = e.getPlayer();
        e.setJoinMessage(null);
        CoinsAPI.createPlayer(hrac.getName());

        hrac.getInventory().clear();
        hrac.setFoodLevel(20);

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

        ChatUtil.sendCenteredMessage(hrac, "§b§lKitPvP");
        hrac.sendMessage("§r");
        hrac.sendMessage("  §a• §fPridan report system §3/report <hrac> <duvod>");
        hrac.sendMessage("  §a• §fPridan novy anticheat §8(§cBETA§8)");
        hrac.sendMessage("§r§f");
        hrac.sendMessage("  §c• §fJe zakazano §cteamovat §fs ostatnimi hraci!");
        hrac.sendMessage("§r§f§g");

        Main.getInstance().killstreaks.put(hrac.getName(), 0);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerAchievement(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDelay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player hrac = e.getPlayer();
        String msg = e.getMessage().replaceAll("%", "%%");

        // - Ranks - //
        String rank = "Silver I";
        if (StatsManager.getKills(hrac) == 250) {
            rank = "Silver II";

            ChatUtil.sendCenteredMessage(hrac, "§r");
            ChatUtil.sendCenteredMessage(hrac, "§b§lLevel Up");
            ChatUtil.sendCenteredMessage(hrac, "§r§f");
            ChatUtil.sendCenteredMessage(hrac, "§3You are promoted to rank" + rank);
            ChatUtil.sendCenteredMessage(hrac, "§r§f§g");
            ChatUtil.sendCenteredMessage(hrac, "§e+250 coins");
            ChatUtil.sendCenteredMessage(hrac, "§r§f§s§g");
        }
        if (StatsManager.getKills(hrac) == 500) {
            rank = "Silver III";
        }
        if (StatsManager.getKills(hrac) == 750) {
            rank = "Silver IV";
        }
        if (StatsManager.getKills(hrac) == 1000) {
            rank = "Gold I";
        }
        if (StatsManager.getKills(hrac) == 1250) {
            rank = "Gold II";
        }
        if (StatsManager.getKills(hrac) == 1500) {
            rank = "Gold III";
        }
        if (StatsManager.getKills(hrac) == 1750) {
            rank = "Gold IV";
        }
        if (StatsManager.getKills(hrac) == 2000) {
            rank = "Diamond I";
        }
        if (StatsManager.getKills(hrac) == 2250) {
            rank = "Diamond II";
        }
        if (StatsManager.getKills(hrac) == 2500) {
            rank = "Diamond III";
        }
        if (StatsManager.getKills(hrac) == 2750) {
            rank = "Diamond IV";
        }
        if (StatsManager.getKills(hrac) == 3000) {
            rank = "Supreme";
        }
        if (StatsManager.getKills(hrac) == 3500) {
            rank = "Global";
        }

        e.setFormat("§8[§b" + rank + "§8]" + " §3" + hrac.getName() + " §8• §7" + msg);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event)
    {
        if(event.getBlock().getType() == Material.ICE)
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event)
    {
        if (((event.getBlock().getType() == Material.ICE) || (event.getBlock().getType() == Material.SNOW) || (event.getBlock().getType() == Material.SNOW_BLOCK))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event)
    {
        event.setCancelled(true);
    }


    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event)
    {
        Block block = event.getBlock();
        if (block.getType() == Material.TORCH) {
            event.setBuildable(true);
        }
    }

    @EventHandler
    public void onPlaceI(BlockIgniteEvent e) {
        Player p = e.getPlayer();
        if (!players.contains(p)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onSpread(BlockSpreadEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(e.toWeatherState());
    }
}
