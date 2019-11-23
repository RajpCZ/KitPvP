package cz.rajp.kitbattle.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopManager {

    public static Inventory shop = Bukkit.createInventory(null, 27, "§bKit Selector");

    public static void openShopMenu(Player hrac) {

        //TANK
        int tank_price = 900;
        String tank_rarity = "Common";

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
        tlore.add("§fCena: §3" + tank_price);
        tlore.add("§fRarity: §3" + tank_rarity);
        tlore.add("§f§3§g§y");
        if (hrac.hasPermission("kitpvp.tank")) {
            tlore.add("§eZakoupeno!");
        } else {
            tlore.add("§eKlikni pro nakup!");
        }

        tmeta.setLore(tlore);
        tank.setItemMeta(tmeta);

        //MORE

        //SET ITEMS
        shop.setItem(10, tank);

        hrac.openInventory(shop);
    }
}
