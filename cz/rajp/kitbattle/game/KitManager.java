package cz.rajp.kitbattle.game;

import cz.rajp.kitbattle.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KitManager {

    public static Inventory kits = Bukkit.createInventory(null, 27, "§bKit Selector");

    public static void openKitMenu(Player hrac) {
        //DEFAULT
        ItemStack def = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta dmeta = def.getItemMeta();
        dmeta.setDisplayName("§3Default");
        ArrayList<String> dlore = new ArrayList<String>();
        dlore.add("§r");
        dlore.add("§fObsahuje:");
        dlore.add("§8- §7Iron Sword 1x §8(§fSharpness I§8)");
        dlore.add("§f§r");
        dlore.add("§8- §7Iron Helmet 1x");
        dlore.add("§8- §7Iron Chestplate 1x");
        dlore.add("§8- §7Leather Leggings 1x");
        dlore.add("§8- §7Iron Boots 1x");
        dlore.add("§f§r§g");
        dlore.add("§eKlikni pro vyber!");

        dmeta.setLore(dlore);
        def.setItemMeta(dmeta);

        //TANK
        ItemStack tank = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta tmeta = tank.getItemMeta();
        tmeta.setDisplayName("§3Tank");
        ArrayList<String> tlore = new ArrayList<String>();
        tlore.add("§r");
        tlore.add("§fObsahuje:");
        tlore.add("§8- §7Stone Sword 1x §8(§fSharpness I§8)");
        tlore.add("§f§r");
        tlore.add("§8- §7Diamond Helmet 1x");
        tlore.add("§8- §7Diamond Chestplate 1x");
        tlore.add("§8- §7Iron Leggings 1x");
        tlore.add("§8- §7Diamond Boots 1x");
        tlore.add("§f§r§g");
        tlore.add("§eKlikni pro vyber!");

        tmeta.setLore(tlore);
        tank.setItemMeta(tmeta);

        //MORE

        //SET ITEMS
        kits.setItem(10, def);
        if (hrac.hasPermission("kitpvp.tank")) {
            kits.setItem(11, tank);
        } else {

        }

        hrac.openInventory(kits);
    }
}
